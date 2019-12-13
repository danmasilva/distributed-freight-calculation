package com.sd.dfc.controller;

import java.util.List;

public interface DataController {
	
    //insere o dado no database e escreve o comando no arquivo, retorna o id do objeto criado
    long insert(String[] splittedList) throws Exception;

    //atualiza o dado no database e escreve o comando no arquivo
    byte[] update(String[] splittedMessage) throws Exception;

    //remove o dado no database e escreve o comando no arquivo
    byte[] delete(String[] splittedMessage) throws Exception;
    
    //le o dado no database indicado
    List<?> readAll(String[] splittedMessage);
    
}
