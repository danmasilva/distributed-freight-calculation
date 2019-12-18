package com.sd.dfc.chord;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Node {

	public static int localId;
	private InetSocketAddress localAddress;
	private HashMap<Integer, InetSocketAddress> fingerAddress;
	private HashMap<Integer, Integer> fingerId;
	public static int hole_size;
	public static int ring_size;
	public static int qtd_nodes;

	private Listener listener;

	public Node() {
	}

	public Node(InetSocketAddress address, int qtd_nodes, int node_id, String contact_ip, int contact_port) {

		localAddress = address;

		Node.ring_size = (int) Math.pow(2, qtd_nodes);
		Node.qtd_nodes = qtd_nodes;

		// initialize an empty finger table
		fingerAddress = new HashMap<>();
		fingerId = new HashMap<>();

		// espaço entre nós no anel
		Node.hole_size = ring_size / qtd_nodes;

		Node.localId = node_id * hole_size;

		for (int i = 0; i < ring_size; i++) {
			if (i >= localId && i < localId + hole_size) {
				updateIthFingerAddress(i, localAddress);
			} else {
				updateIthFingerAddress(i, null);
			}
		}
		for (int i = 0; i < qtd_nodes; i++) {
			int value = localId + (int) Math.pow(2, i);

			// encontra nó sucessor
			value = (((value + hole_size) / hole_size) * hole_size) % ring_size;
			updateIthFingerId(i, value);
		}

		if (contact_ip != null) {
			InetSocketAddress contact = new InetSocketAddress(contact_ip, contact_port);
			join(contact);
		}

		// initialize threads
		listener = new Listener(this);
		listener.start();
	}

	public boolean join(InetSocketAddress contact) {

		// if contact is other node (join ring), try to contact that node
		// (contact will never be null)
		if (contact != null && !contact.equals(localAddress)) {
			String[] contact_finger = Helper.requestAddress(contact,
					"FINDID_" + localId + "_" + localAddress.getHostString() + "_" + localAddress.getPort());
			if (contact_finger == null) {
				System.out.println("\nCannot find node you are trying to contact. Please exit.\n");
				return false;
			}

			// atualiza sua finger com a finger do server contatado.
			for (int i = 1; i < contact_finger.length; i += 2) {
				int id = Integer.parseInt(contact_finger[i]);
				String ip = contact_finger[i + 1].split(":")[0];
				int port = Integer.parseInt(contact_finger[i + 1].split(":")[1]);
				InetSocketAddress address = new InetSocketAddress(ip, port);
				updateIthFingerAddress(id, address);
			}

		}

		// envia para todos os threads conhecidos a nova finger.
		for (int i = 0; i < ring_size; i += hole_size) {
			if (fingerAddress.get(i) != null) {
				InetSocketAddress address = new InetSocketAddress(fingerAddress.get(i).getHostName(),
						fingerAddress.get(i).getPort());
				Helper.requestAddress(address, "UPDATEFINGER_" + fingerToString());
			}
		}

		return true;
	}

	public String fingerToString() {
		StringBuilder result = new StringBuilder();
		Iterator it = fingerAddress.entrySet().iterator();

		fingerAddress.forEach((key, value) -> {
			if (value != null) {
				result.append("_" + key + "_" + value);
			}
		});
		return result.substring(1);
	}

	public Map<Integer, InetSocketAddress> stringToFinger(String[] stringFinger) {
		Map<Integer, InetSocketAddress> newFinger = new HashMap<>();
		for (int i = 1; i < stringFinger.length; i += 2) {
			int id = Integer.parseInt(stringFinger[i]);
			String[] address = stringFinger[i + 1].split(":");
			InetSocketAddress address1 = new InetSocketAddress(address[0], Integer.parseInt(address[1]));
			newFinger.put(id, address1);
		}

		return newFinger;
	}

	public void fillJoinedNode(int id, InetSocketAddress address) {
		// int fingerId = id * hole_size;
		for (int i = id; i < id + hole_size; i++) {
			updateIthFingerAddress(i, address);
		}
		//se o nó é o nó de maior id no chord, ele fica responsável pelos nós no final do anel
		//TODO: CORRIGIR STATEMENT
		if (id == (qtd_nodes * hole_size) - hole_size) {
			for (int i = ring_size - 1; i >= id; i--) {
				updateIthFingerAddress(i, address);
			}
		}
	}

	private void updateIthFingerAddress(int i, InetSocketAddress value) {
		fingerAddress.put(i, value);
	}

	private void updateIthFingerId(int i, int value) {
		fingerId.put(i, value);
	}

	public InetSocketAddress getAddress() {
		return localAddress;
	}

	public void updateFinger(String[] responseFinger) {
		this.fingerAddress = (HashMap<Integer, InetSocketAddress>) stringToFinger(responseFinger);
		System.out.println("finger updated.\n " + fingerAddress.toString());
	}

	// métodos para lidar com requisições de dados
	public String sendInsertQuery(int hashValue, String[] splittedMessage) {
		String queryType = (splittedMessage[0] + splittedMessage[1]).toUpperCase();
		String response = null;
		for (int i = 0; i < fingerId.size(); i++) {
			int value = fingerId.get(i);
			if (hashValue >= value && hashValue < value + hole_size) {
				response = Helper.sendRequest(fingerAddress.get(fingerId.get(i)),
						queryType + "_" + Helper.toString(splittedMessage));
				break;
			}
		}

		// se nao há candidato na finger table, mandar para o maior possível:
		if (response == null) {
			response = Helper.sendRequest(fingerAddress.get(fingerId.get(fingerId.size() - 1)),
					queryType + "_" + Helper.toString(splittedMessage));
		}
		if (response.startsWith("CREATESUCC_")) {
			return response.split("_")[1];
		} else
			return null;
	}

	public byte[] sendUpdateQuery(int hashValue, String[] splittedMessage) {
		String queryType = (splittedMessage[0] + splittedMessage[1]).toUpperCase();
		String response = null;
		for (int i = 0; i < fingerId.size(); i++) {
			int value = fingerId.get(i);
			if (hashValue >= value && hashValue < value + hole_size) {
				response = Helper.sendRequest(fingerAddress.get(fingerId.get(i)),
						queryType + "_" + Helper.toString(splittedMessage));
				break;
			}
		}

		// se nao há candidato na finger table, mandar para o maior possível:
		if (response == null) {
			response = Helper.sendRequest(fingerAddress.get(fingerId.get(fingerId.size())),
					queryType + "_" + Helper.toString(splittedMessage));
		}
		if (response.startsWith("CREATESUCC_")) {
			return response.split("_")[1].getBytes();
		} else
			return null;
	}


}
