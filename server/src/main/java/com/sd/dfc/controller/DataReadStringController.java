package com.sd.dfc.controller;

//Contém o método de leitura que retorna uma string com os dados, utilizado no código legado.
public interface DataReadStringController {
	//le o dado no database indicado
    String readAll(String[] splittedMessage);
}
