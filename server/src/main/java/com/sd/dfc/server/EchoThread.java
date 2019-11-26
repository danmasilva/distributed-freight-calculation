package com.sd.dfc.server;

import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class EchoThread extends Thread {

    private Socket socket;
    private DataController dataController = new DataControllerImpl();

    EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    //valida se comando é válido
                    if(dataController.validCommand(inputLine)!=null){
                        dataController.putData(out, inputLine);
                    }else{
                        out.println("Comando mal formatado ou inválido.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("O cliente se desconectou do servidor!");
        }
    }
}
