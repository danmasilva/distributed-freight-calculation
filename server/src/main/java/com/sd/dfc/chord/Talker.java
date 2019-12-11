package com.sd.dfc.chord;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Talker implements Runnable {
	Socket talkSocket;
	private Node local;

	public Talker(Socket _talkSocket, Node _local) {
		talkSocket = _talkSocket;
		local = _local;
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
			String[] splittedMessage = request.split("_");
			int id = Integer.parseInt(request.split("_")[1]);
			String address = request.split("_")[2];
			int port = Integer.parseInt(request.split("_")[3]);
			InetSocketAddress joinedNode = new InetSocketAddress(address, port);

			//local.fillJoinedNode(id, joinedNode);

			result = local.fingerToString();
			
			ret = "FOUNDSUCC_" + result;

		} else if (request.startsWith("KEEP")) {
			ret = "ALIVE";
		}
		return ret;
	}
}
