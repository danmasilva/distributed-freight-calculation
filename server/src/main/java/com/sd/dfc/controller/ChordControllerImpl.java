package com.sd.dfc.controller;

public class ChordControllerImpl implements ChordController{

	@Override
	public int hashData(String data, int ring_size) {
		int i = Math.abs(data.hashCode());
		return i % ring_size;
	}

	@Override
	public boolean responsibleForData(int hash_value, int node_id, int hole_size) {
		int max_range = node_id+hole_size;
		return hash_value >= node_id && hash_value < max_range;
	}
	
}
