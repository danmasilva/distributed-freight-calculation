package com.sd.dfc.chord;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerCepsImpl;
import com.sd.dfc.controller.DataControllerTransportadoraImpl;

public class Talker extends Thread {
	Socket talkSocket;
	private Node local;
	DataController cepDataController;
	DataController transportadoraDataController;

	public Talker(Socket _talkSocket, Node _local) {
		talkSocket = _talkSocket;
		local = _local;
		cepDataController = new DataControllerCepsImpl();
		transportadoraDataController = new DataControllerTransportadoraImpl();
	}

	public void run() {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = talkSocket.getInputStream();
			String request = Helper.inputStreamToString(input);
			String response = processRequest(request);
			if (response != null) {
				output = talkSocket.getOutputStream();
				output.write(response.getBytes());
			}
			input.close();
		} catch (IOException e) {
			throw new RuntimeException("Cannot talk.\nServer port: " + local.getAddress().getPort() + "; Talker port: "
					+ talkSocket.getPort(), e);
		}
	}

	private String processRequest(String request) {
		String result = null;
		String ret = null;
		if (request == null) {
			return null;
		}

		if (request.startsWith("FINDID_")) {
			System.out.println("olá");
			String[] splittedMessage = request.split("_");
			int id = Integer.parseInt(request.split("_")[1]);
			String address = request.split("_")[2];
			int port = Integer.parseInt(request.split("_")[3]);
			InetSocketAddress joinedNode = new InetSocketAddress(address, port);

			local.fillJoinedNode(id, joinedNode);

			result = local.fingerToString();

			ret = "FOUNDSUCC_" + result;
		} else if (request.startsWith("UPDATEFINGER_")) {
			local.updateFinger(request.split("_"));
		} else if (request.startsWith("CREATECEP")) {
			try {
				ret = ("CREATESUCC_") + String.valueOf(cepDataController.insert(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}

		} else if (request.startsWith("UPDATECEP")) {
			try {
				ret = ("CREATESUCC_") + String.valueOf(cepDataController.update(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}
		} else if (request.startsWith("DELETECEP")) {
			try {
				ret = ("CREATESUCC_") + String.valueOf(cepDataController.delete(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}

		} else if (request.startsWith("CREATETRANSPORTADORA")) {
			try {
				ret = ("CREATESUCC_")
						+ String.valueOf(transportadoraDataController.insert(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}
		} else if (request.startsWith("UPDATETRANSPORTADORA")) {
			try {
				ret = ("CREATESUCC_")
						+ String.valueOf(transportadoraDataController.update(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}
		} else if (request.startsWith("DELETETRANSPORTADORA")) {
			try {
				ret = ("CREATESUCC_")
						+ String.valueOf(transportadoraDataController.insert(request.split("_")[1].split(" ")));
			} catch (Exception e) {
				ret = "CREATEFAILURE";
				e.printStackTrace();
			}
		}
		return ret;
	}
}
