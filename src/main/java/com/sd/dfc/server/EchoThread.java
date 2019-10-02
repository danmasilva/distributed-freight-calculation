package com.sd.dfc.server;

import com.sd.dfc.client.SocketClient;
import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class EchoThread extends Thread {

    private Socket socket;
    DataController dataController = new DataControllerImpl();

    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    dataController.putData(out, inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("O cliente se desconectou do servidor!");
//            e.printStackTrace();
        }
    }
}
