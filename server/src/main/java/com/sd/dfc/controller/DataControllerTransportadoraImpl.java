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
import com.sd.dfc.model.Transportadora;
import com.sd.dfc.server.GRPCServer;

public class DataControllerTransportadoraImpl implements DataController {

	private final String fileName = "transportadora.snap";
	private final String headerName = "transportadoraHeader.txt";

	private ArchiveManipulator transportadoraArchive = new ArchiveManipulator(this.fileName);
	private HeaderManipulator transportadoraHeader = new HeaderManipulator(this.headerName);

	public DataControllerTransportadoraImpl() {
		// cria snap a cada 30 segundos.
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					transportadoraArchive.createSnapshot("transportadora", transportadoraHeader);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 30000);
	}

	@Override
	public long insert(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		long createdId;

		try {
			createdId = GRPCServer.transportadoraDatabase
					.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
			transportadoraArchive.write(String.join(" ", splittedList));
		} catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return -1;
		}
		return createdId;
	}

	@Override
	public byte[] update(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));

		try {
			transportadoraArchive.write(String.join(" ", splittedList));
			return GRPCServer.transportadoraDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
					String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
		} catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<?> readAll(String[] splittedMessage) {
		Map<BigInteger, byte[]> map;
		List<Transportadora> listTransportadoras = new ArrayList<>();

		map = GRPCServer.transportadoraDatabase.readAll();

		for (Map.Entry<BigInteger, byte[]> entry : map.entrySet()) {
			String[] transportadoraValues = new String(entry.getValue()).split(" ");
			String[] cepValues = new String(
					GRPCServer.cepDatabase.read(BigInteger.valueOf(Long.parseLong(transportadoraValues[1]))))
							.split(" ");

			Ceps ceps = new Ceps();
			ceps.setId(Long.parseLong(transportadoraValues[1]));
			ceps.setCepInicio(Long.parseLong(cepValues[0]));
			ceps.setCepFim(Long.parseLong(cepValues[1]));

			Transportadora transportadora = new Transportadora(Long.parseLong(entry.getKey().toString()),
					transportadoraValues[0], ceps, Double.parseDouble(transportadoraValues[2]));

			listTransportadoras.add(transportadora);
		}
		return listTransportadoras;
	}

	@Override
	public byte[] delete(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		try {
			transportadoraArchive.write(String.join(" ", splittedList));
			return GRPCServer.transportadoraDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
		} catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return null;
		}
	}

}
