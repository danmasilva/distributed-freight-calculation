package com.sd.dfc.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.lang3.SerializationUtils;

import com.sd.dfc.server.GRPCServer;

public class ArchiveManipulator {

	private String dataSource;

	public ArchiveManipulator(String dataSource) {
		this.dataSource = dataSource;
	}

	public ArchiveManipulator() {
	}

	public synchronized void write(String text) throws IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataSource, true)))) {
			writer.append(text).append("\n");
		}
	}

	public boolean deleteFile(String fileName) {
		File file = new File(fileName);
		return file.delete();
	}
	
	public boolean clearFile(String fileName) {
		try {
			new PrintWriter(fileName).close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createSnapshot(String database, HeaderManipulator header) throws IOException {
		if (database.equals("cep") || database.equals("transportadora")) {
			String path = database + ".snap." + header.getSnapCount();
			File snap = new File(path);
			snap.createNewFile();
			Files.write(Paths.get(path), database.equals("cep") ? SerializationUtils.serialize(GRPCServer.cepDatabase)
					: SerializationUtils.serialize(GRPCServer.transportadoraDatabase));

			header.notifySnapCreated();

			if (header.getSnapCount() - header.getLowerSnapId() > 3) {
				deleteFile(database + ".snap." + header.getLowerSnapId());
				header.notifySnapDeleted();
			}
			
			//após a criação do snapshot, pode-se limpar o buffer.
			return this.clearFile(database + ".snap") && header.writeHeader();
		}
		return false;
	}
}
