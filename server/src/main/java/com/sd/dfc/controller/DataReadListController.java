package com.sd.dfc.controller;

import java.util.List;

//Contém o método de leitura que retorna uma lista de dados, utilizado no retorno do grpc.
public interface DataReadListController {
	//le o dado no database indicado
    List<?> readAll(String[] splittedMessage);
}
