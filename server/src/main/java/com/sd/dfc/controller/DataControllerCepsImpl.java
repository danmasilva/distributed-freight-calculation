package com.sd.dfc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sd.dfc.data.ArchiveManipulation;
import com.sd.dfc.data.ArchiveManipulationImpl;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.server.GRPCServer;

public class DataControllerCepsImpl implements DataReadListController, DataController {
	
	private final String cep = "cep.txt";
	private ArchiveManipulation cepArchive = new ArchiveManipulationImpl(this.cep);

	@Override
	public long insert(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
        long createdId;
        
        try {
        	createdId = GRPCServer.cepDatabase.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
        	cepArchive.write(String.join(" ", splittedList));        	
        }
        catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return -1;
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
            listCeps.add(new Ceps(Long.parseLong(entry.getKey().toString()),Long.parseLong(values[0]), Long.parseLong(values[1])));
        }
		return listCeps;
	}

	@Override
	public byte[] update(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		
		try {
			cepArchive.write(String.join(" ", splittedList));
			return GRPCServer.cepDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
					String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());			
		}
		catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public byte[] delete(String[] splittedMessage) throws IOException {
		List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
		try {
			cepArchive.write(String.join(" ", splittedList));
            return GRPCServer.cepDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
		}
		catch (Exception e) {
			System.err.println("Ocorreu algum erro na base de dados.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Deprecated
	public String validCommand(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public boolean putData(PrintWriter out, String data) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
