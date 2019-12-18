package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;

public class Database extends ArchiveManipulator implements Serializable {

	private static final long serialVersionUID = 849942648731843734L;

	private final int CEP= 3;

	private final int TRASNSPORTADORA = 2;

	private Map<String, byte[]> map = new HashMap<>();

	public Database(String filename) {
		this.recoverData(filename);
	}

	public Database() {
	}

	// insere o vetor de bytes e retorna o id mapeado para o mesmo
	public String create(String key, byte[] value) {
		map.put(key, value);
		return key;
	}

	public byte[] read(BigInteger id) {
		return map.get(id);
	}

	public Map<String, byte[]> readAll() {
		return map;
	}

	public byte[] update(String id, byte[] value) {
		return map.put(id, value);
	}

	public byte[] delete(String id) {
		return map.remove(id);
	}

	private void recoverData(String fileName) {

		if(fileName.equals("cep")||fileName.equals("transportadora")) {
			// primeiro, recuperar o snap mais recente disponível
			HeaderManipulator header = fileName == "cep" ? new HeaderManipulator("cepHeader.txt")
					: new HeaderManipulator("transportadoraHeader.txt");
			
			Database wrapper;
			
			try {
				wrapper = SerializationUtils.deserialize(this.getSnapshot(fileName + ".snap." + (header.getSnapCount()-1)));
			} catch (Exception e) {
				wrapper = null;
			}
			if(wrapper != null) {
				this.map = wrapper.map;
			}			
		}
		
		//após, ler o buffer de comandos
		try {
			FileReader file = new FileReader(fileName+".snap");
			BufferedReader readFile = new BufferedReader(file);
			String line;
			String[] splittedCommand;
			while ((line = readFile.readLine()) != null) {
				splittedCommand = line.split(" ");

				// lista com o comando subtraido do método e do nome do arquivo
				List<String> splittedList = Arrays.asList(splittedCommand);


				switch (splittedCommand[0]) {
				case "create":
					if(splittedCommand[1].equals("cep")) {
						this.create(splittedCommand[2], String.join(" ", splittedList.subList(CEP, splittedList.size())).getBytes());
					}  else {
						this.create(splittedCommand[2], String.join(" ", splittedList.subList(TRASNSPORTADORA, splittedList.size())).getBytes());
					}

					break;
				case "update":
					this.update(splittedList.get(2),
							String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
					break;
				case "delete":
					this.delete(splittedList.get(2));
					break;
				}
			}
			readFile.close();

		} catch (Exception e) {
			System.err.println("Não há buffer de comandos em "+ fileName + " para ser lido.");
		}
	}

	public byte[] getSnapshot(String snapName) {
		try {
			return Files.readAllBytes(Paths.get(snapName));
		}catch (Exception e) {
			System.err.println("snap " + snapName + " not found!");
			return null;
		}
	}
	
}
