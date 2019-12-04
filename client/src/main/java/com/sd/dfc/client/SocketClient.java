package com.sd.dfc.client;

import com.sd.dfc.config.ReadPropertyFile;
import com.sd.dfc.principal.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public static void main(String[] args) {
        SocketClient server = new SocketClient();
        ReadPropertyFile prop = new ReadPropertyFile();

        try {
            int port = Integer.parseInt(prop.getValue("dfc.client.port")) ;
            String ip = prop.getValue("dfc.client.url");
            
            server.startConnection(ip , port);
            Menu menu = new Menu();
            System.out.println(menu.presentMenu());

            //BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
            Scanner s = new Scanner(System.in);
            String text;

            while (true) {

                text = s.nextLine();

                if (!(text).equals("sair") && !(text).equals("quit") && !(text).equals("exit")) {
                    System.out.println(server.sendMessage(text));
                } else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}