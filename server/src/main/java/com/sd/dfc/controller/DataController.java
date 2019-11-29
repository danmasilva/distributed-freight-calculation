package com.sd.dfc.controller;

import java.io.IOException;
import java.io.PrintWriter;

public interface DataController {
    //insere o dado no database e escreve o comando no arquivo, retorna o id do objeto criado
    long insert(String[] splittedList) throws IOException;

    //atualiza o dado no database e escreve o comando no arquivo
    byte[] update(String[] splittedMessage) throws IOException;

    //remove o dado no database e escreve o comando no arquivo
    byte[] delete(String[] splittedMessage) throws IOException;
    
    //verifica se é um comando válido. se for, retorna em qual base deve atuar.
    String validCommand(String input);

    //verifica o que o comando deseja fazer, em qual banco deseja, e o faz.
    boolean putData(PrintWriter out, String data) throws IOException;
    
}
