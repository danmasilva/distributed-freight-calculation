package com.sd.dfc.chord;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Node {

	private int localId;
	private InetSocketAddress localAddress;
	private HashMap<Integer, InetSocketAddress> fingerAddress;
	private HashMap<Integer, Integer> fingerId;

	private Listener listener;

	public Node(InetSocketAddress address, int qtd_nodes, int node_id, String contact_ip, int contact_port) {

		localAddress = address;

		int ring_size = (int) Math.pow(2, qtd_nodes);

		// initialize an empty finger table
		fingerAddress = new HashMap<>();
		fingerId = new HashMap<>();

		// espaço entre nós no anel
		int hole_size = ring_size / qtd_nodes;

		localId = node_id * hole_size;

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
			value = ((value + hole_size) / hole_size) * hole_size;
			updateIthFingerId(i, value);
		}

		if (contact_ip != null) {
			InetSocketAddress contact = new InetSocketAddress(contact_ip, contact_port);
			join(contact, hole_size);
		}

		// initialize threads
		listener = new Listener(this);
	}

	public boolean join(InetSocketAddress contact, int hole_size) {

		// if contact is other node (join ring), try to contact that node
		// (contact will never be null)
		if (contact != null && !contact.equals(localAddress)) {
			Integer contact_id = Helper.requestAddress(contact, "FINDID_" + localId);
			if (contact_id == null) {
				System.out.println("\nCannot find node you are trying to contact. Please exit.\n");
				return false;
			}
			for(int i=0; i<hole_size; i++) {
				updateIthFingerAddress(contact_id+i, contact);
			}
		}

		// start all threads
		listener.start();

		return true;
	}
	
	public String fingerToString() {
		StringBuilder result = new StringBuilder();
		Iterator it = fingerAddress.entrySet().iterator();
	    while (it.hasNext()) {
	    	 Map.Entry pair = (Map.Entry)it.next();
	    	 result.append("_" + pair.getKey() + "_" + pair.getValue());
		}
		
		return result.substring(1);
	}

	public String notify(InetSocketAddress successor) {
		if (successor != null && !successor.equals(localAddress))
			return Helper.sendRequest(successor,
					"IAMPRE_" + localAddress.getAddress().toString() + ":" + localAddress.getPort());
		else
			return null;
	}

	public InetSocketAddress find_successor(long id) {

		// initialize return value as this node's successor (might be null)
		InetSocketAddress ret = this.getSuccessor();

		return ret;
	}

	private void updateIthFingerAddress(int i, InetSocketAddress value) {
		fingerAddress.put(i, value);

		// if the updated one is successor, notify the new successor
//		if (i == 1 && value != null && !value.equals(localAddress)) {
		// notify(value);
		// }
	}

	private void updateIthFingerId(int i, int value) {
		fingerId.put(i, value);
	}

	public long getId() {
		return localId;
	}

	public InetSocketAddress getAddress() {
		return localAddress;
	}

	public InetSocketAddress getSuccessor() {
		if (fingerAddress != null && fingerAddress.size() > 0) {
			return fingerAddress.get(0);
		}
		return null;
	}
}
