package com.sd.dfc.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.sd.dfc.data.ArchiveManipulator;
import com.sd.dfc.data.HeaderManipulator;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.server.GRPCServer;

public class DataControllerCepsImpl implements DataController {

	private final String fileName = "cep.snap";
	private final String headerName = "cepHeader.txt";

	private ArchiveManipulator cepArchive = new ArchiveManipulator(this.fileName);
	private HeaderManipulator cepHeader = new HeaderManipulator(headerName);

	public DataControllerCepsImpl() {
		// cria snap a cada 30 segundos.
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
		}, 0, 30000);
	}

	@Override
	public long insert(String[] splittedMessage) throws Exception {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		long createdId;

		try {
			createdId = GRPCServer.cepDatabase
					.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
			cepArchive.write(String.join(" ", splittedList));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return createdId;
	}

	@Override
	public List<?> readAll(String[] splittedMessage) {
		Map<BigInteger, byte[]> map;
		List<Ceps> listCeps = new ArrayList<Ceps>();

		map = GRPCServer.cepDatabase.readAll();
		for (Map.Entry<BigInteger, byte[]> entry : map.entrySet()) {
			String[] values = new String(entry.getValue()).split(" ");
			listCeps.add(new Ceps(Long.parseLong(entry.getKey().toString()), Long.parseLong(values[0]),
					Long.parseLong(values[1])));
		}
		return listCeps;
	}

	@Override
	public byte[] update(String[] splittedMessage) throws Exception {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));

		try {
			cepArchive.write(String.join(" ", splittedList));
			return GRPCServer.cepDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
					String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public byte[] delete(String[] splittedMessage) throws Exception {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		try {
			cepArchive.write(String.join(" ", splittedList));
			return GRPCServer.cepDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public void scheduleSnapshot() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Hello World");
			}
		}, 0, 5000);
	}

}
