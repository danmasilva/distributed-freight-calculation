package com.sd.dfc.server;

import com.sd.dfc.data.Database;
import com.sd.dfc.data.Database2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {

    private static final int PORT = 12345;
    public static Database2 cepDatabase = null;
    public static Database2 transportadoraDatabase = null;


    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            cepDatabase = new Database2("cep.txt");
            transportadoraDatabase = new Database2("transportadora.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert serverSocket != null;
                socket = serverSocket.accept();
                System.out.println("Connection accepted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // cria thread para o cliente
            new EchoThread(socket).start();
        }
    }

}
