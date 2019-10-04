package com.sd.dfc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface DataController {
    //insere o dado no database e escreve o comando no arquivo
    boolean insert(String[] splittedList) throws IOException;

    //le o dado no database indicado
    void readAll(String[] splittedMessage);

    //atualiza o dado no database e escreve o comando no arquivo
    byte[] update(String[] splittedMessage) throws IOException;

    //remove o dado no database e escreve o comando no arquivo
    byte[] delete(String[] splittedMessage) throws IOException;

    //verifica se é um comando válido. se for, retorna em qual base deve atuar.
    String validCommand(String input);

    //verifica o que o comando deseja fazer, em qual banco deseja, e o faz.
    boolean putData(PrintWriter out, String data) throws IOException;
}
