package com.sd.dfc.server;

import com.sd.dfc.data.Database;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {

    private static final int PORT = 12345;
    public static Database database = null;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            database = new Database(true);
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
