package com.sd.dfc.controller;

public interface ChordController {
	// calcula a hash da requisição realizada para o chord.
	int hashData(String data, int ring_size);

	// retorna true se esse servidor é o responsável para lidar com aquele valor.
	boolean responsibleForData(int hash_value, int node_id, int hole_size);
}
