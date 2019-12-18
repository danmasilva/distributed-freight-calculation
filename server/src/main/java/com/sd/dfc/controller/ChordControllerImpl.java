package com.sd.dfc.controller;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class ChordControllerImpl implements ChordController{

	@Override
	public int hashData(String data, int ring_size) {
		int hash = -1;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte[] messageDigest = algorithm.digest(data.getBytes());
			hash = ByteBuffer.wrap(messageDigest).getInt();
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return  Math.abs(hash)% ring_size;
	}

	@Override
	public boolean responsibleForData(int hash_value, int node_id, int hole_size) {
		int max_range = node_id+hole_size;
		return hash_value >= node_id && hash_value < max_range;
	}
	
}
