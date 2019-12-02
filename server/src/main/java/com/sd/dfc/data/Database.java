package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Database extends ArchiveManipulator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 849942648731843734L;
	
	private Map<BigInteger, byte[]> map = new HashMap<>();
	private AtomicInteger count = new AtomicInteger();

	public Database(String filename) {
		this.recoverData(filename);
	}

	public Database() {
	}

	// insere o vetor de bytes e retorna o id mapeado para o mesmo
	public long create(byte[] value) {
		map.put(BigInteger.valueOf(count.get()), value);
		return count.incrementAndGet();
	}

	public byte[] read(BigInteger id) {
		return map.get(id);
	}

	public Map<BigInteger, byte[]> readAll() {
		return map;
	}

	public byte[] update(BigInteger id, byte[] value) {
		return map.put(id, value);
	}

	public byte[] delete(BigInteger id) {
		return map.remove(id);
	}

	private void recoverData(String fileName) {

		try {
			FileReader file = new FileReader(fileName);
			BufferedReader readFile = new BufferedReader(file);
			String line;
			String[] splittedCommand;
			while ((line = readFile.readLine()) != null) {
				splittedCommand = line.split(" ");

				// lista com o comando subtraido do método e do nome do arquivo
				List<String> splittedList = Arrays.asList(splittedCommand);

				switch (splittedCommand[0]) {
				case "create":
					this.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
					break;
				case "update":
					this.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
							String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
					break;
				case "delete":
					this.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
					break;
				}
			}
			readFile.close();

		} catch (Exception e) {
			System.err.println(Arrays.toString(e.getStackTrace()));
		}
	}

	public byte[] toBytes() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		
		try {
			  out = new ObjectOutputStream(bos);   
			  out.writeObject(this);
			  out.flush();
			 return bos.toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
