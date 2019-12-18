package com.sd.dfc.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.sd.dfc.chord.Helper;
import com.sd.dfc.chord.Node;
import com.sd.dfc.data.ArchiveManipulator;
import com.sd.dfc.data.HeaderManipulator;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.server.GRPCServer;

public class DataControllerCepsImpl implements DataController {

	private final String fileName = "cep.snap";
	private final String headerName = "cepHeader.txt";

	private ArchiveManipulator cepArchive = new ArchiveManipulator(this.fileName);
	private HeaderManipulator cepHeader = new HeaderManipulator(headerName);

	ChordController chordController = new ChordControllerImpl();

	public DataControllerCepsImpl() {
		// cria snap a cada 60 segundos.
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					cepArchive.createSnapshot("cep", cepHeader);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 60000);
	}

	@Override
	public String insert(String[] splittedMessage) throws Exception {

		int hashValue = chordController.hashData(splittedMessage[2], Node.ring_size);
		if (chordController.responsibleForData(hashValue, Node.localId, Node.hole_size)) {

			List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
			String key;

			try {
				key = GRPCServer.cepDatabase
						.create(splittedList.get(2), String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
				cepArchive.write(String.join(" ", splittedList));
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
			return key;
		} else {
			return GRPCServer.m_node.sendInsertQuery(hashValue, splittedMessage);
		}
	}

	@Override
	public List<?> readAll(String[] splittedMessage) {
		Map<String, byte[]> map;
		List<Ceps> listCeps = new ArrayList<Ceps>();

		map = GRPCServer.cepDatabase.readAll();
		for (Map.Entry<String, byte[]> entry : map.entrySet()) {
			String[] values = new String(entry.getValue()).split(" ");
			listCeps.add(new Ceps(entry.getKey(), Long.parseLong(values[0]),
					Long.parseLong(values[1])));
		}
		return listCeps;
	}

	@Override
	public byte[] update(String[] splittedMessage) throws Exception {
		int hashValue = chordController.hashData(splittedMessage[2], Node.ring_size);
		if (chordController.responsibleForData(hashValue, Node.localId, Node.hole_size)) {
			List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
			
			try {
				cepArchive.write(String.join(" ", splittedList));
				return GRPCServer.cepDatabase.update(splittedList.get(2),
						String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		} else {
			return GRPCServer.m_node.sendUpdateQuery(hashValue, splittedMessage);
		}
	}

	@Override
	public byte[] delete(String[] splittedMessage) throws Exception {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		try {
			cepArchive.write(String.join(" ", splittedList));
			return GRPCServer.cepDatabase.delete(splittedList.get(2));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

}
