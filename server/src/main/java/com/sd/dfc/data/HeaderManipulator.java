package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeaderManipulator {

	private final String header;

	public HeaderManipulator(String headerName) {
		this.header = headerName;
		this.openHeader();
	}

	// auxiliares disponíveis no arquivo de cabeçalho.
	private int snapCount;
	private int lowerSnapId;

	public int getSnapCount() {
		return snapCount;
	}

	public int getLowerSnapId() {
		return lowerSnapId;
	}

	public boolean createHeader(File file) throws IOException {
		if (!(file.createNewFile())) {
			return false;
		}

		BufferedWriter bf = new BufferedWriter(new FileWriter(file));

		// 0 arquivos de snapshot;.
		bf.write("snap_count 0 lower_snap_id 0");
		bf.close();
		return true;
	}

	public boolean openHeader() {
		File file = new File(header);

		// se arquivo ainda nao existe, criar um novo, inicializar as variáveis e
		// finalizar.
		if (!file.isFile()) {
			try {
				this.createHeader(file);
				snapCount = 0;
				lowerSnapId = 0;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		// se arquivo existe, recuperar as variáveis e finalizar.
		try (BufferedReader r = new BufferedReader(new FileReader(file))) {
			String[] line = r.readLine().split(" ");

			snapCount = Integer.parseInt(line[1]);
			lowerSnapId = Integer.parseInt(line[3]);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean writeHeader() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(header);
			String str = "snap_count " + snapCount + " lower_snap_id " + lowerSnapId;
			pw.println(str);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// um novo snap foi criado. variáveis devem ser atualizadas.
	public boolean notifySnapCreated() {
		++snapCount;
		return writeHeader();
	}

	// um novo snap foi deletado. variáveis devem ser atualizadas.
	public boolean notifySnapDeleted() {
		++lowerSnapId;
		return writeHeader();
	}

}
