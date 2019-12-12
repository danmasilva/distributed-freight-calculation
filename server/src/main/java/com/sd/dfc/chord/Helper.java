package com.sd.dfc.chord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Helper {

	public Helper() {
	}

	// retorna a finger table do servidor conectado.
	public static String[] requestAddress(InetSocketAddress server, String req) {

		// invalid input, return null
		if (server == null || req == null) {
			return null;
		}

		// send request to server
		String response = sendRequest(server, req);

		// if response is null, return null
		if (response == null) {
			return null;
		}

		// server find something,
		// use a resposta para atualizar a finger table local.
		else {
			return response.split("_");
		}
	}

	/**
	 * Send request to server and read response
	 * 
	 * @param server
	 * @param request
	 * @return response, might be null if (1) invalid input (2) cannot open socket
	 *         or write request to it (3) response read by inputStreamToString() is
	 *         null
	 */
	public static String sendRequest(InetSocketAddress server, String req) {

		// invalid input
		if (server == null || req == null)
			return null;

		Socket talkSocket = null;

		// try to open talkSocket, output request to this socket
		// return null if fail to do so
		try {
			String address = server.getHostName() != null ? server.getHostName() : server.getAddress().getHostAddress();
			address = address.replace("/", "");
			talkSocket = new Socket(address, server.getPort());
			PrintStream output = new PrintStream(talkSocket.getOutputStream());
			output.println(req);
		} catch (IOException e) {
			// System.out.println("\nCannot send request to "+server.toString()+"\nRequest
			// is: "+req+"\n");
			return null;
		}

		// sleep for a short time, waiting for response
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// get input stream, try to read something from it
		InputStream input = null;
		try {
			input = talkSocket.getInputStream();
		} catch (IOException e) {
			System.out.println("Cannot get input stream from " + server.toString() + "\nRequest is: " + req + "\n");
		}
		String response = Helper.inputStreamToString(input);

		// try to close socket
		try {
			talkSocket.close();
		} catch (IOException e) {
			throw new RuntimeException("Cannot close socket", e);
		}
		return response;
	}

	public static InetSocketAddress createSocketAddress(String addr) {

		// input null, return null
		if (addr == null) {
			return null;
		}

		// split input into ip string and port string
		String[] splitted = addr.split(":");

		// can split string
		if (splitted.length >= 2) {

			// get and pre-process ip address string
			String ip = splitted[0];
			if (ip.startsWith("/")) {
				ip = ip.substring(1);
			}

			// parse ip address, if fail, return null
			InetAddress m_ip = null;
			try {
				m_ip = InetAddress.getByName(ip);
			} catch (UnknownHostException e) {
				System.out.println("Cannot create ip address: " + ip);
				return null;
			}

			// parse port number
			String port = splitted[1];
			int m_port = Integer.parseInt(port);

			// combine ip addr and port in socket address
			return new InetSocketAddress(m_ip, m_port);
		}

		// cannot split string
		else {
			return null;
		}

	}

	public static String inputStreamToString(InputStream in) {

		// invalid input
		if (in == null) {
			return null;
		}

		// try to read line from input stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.out.println("Cannot read line from input stream.");
			return null;
		}

		return line;
	}

}
