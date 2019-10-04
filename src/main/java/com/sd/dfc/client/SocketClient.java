package com.sd.dfc.client;

import com.sd.dfc.config.ReadPropertyFile;
import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerImpl;
import com.sd.dfc.dao.CepDao;
import com.sd.dfc.dao.CepDaoImpl;
import com.sd.dfc.dao.TransportadoraDao;
import com.sd.dfc.dao.TransportadoraDaoImpl;
import com.sd.dfc.data.ArchiveManipulation;
import com.sd.dfc.data.ArchiveManipulationImpl;
import com.sd.dfc.data.Database;
import com.sd.dfc.principal.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SocketClient {

    public static final String INSERT = "insert";
    public static final String CREATE = "create";
    public static final String INSERIR = "inserir";
    public static final String READ_ALL = "readall";
    public static final String LER_TODOS = "lertodos";
    public static final String UPDATE = "update";
    public static final String CHANGE = "change";
    public static final String ALTERAR = "alterar";
    public static final String DELETE = "delete";
    public static final String DELETAR = "deletar";

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Database data;



    private void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        data = new Database();
    }

    private String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }



    public static void main(String[] args) {
        SocketClient server = new SocketClient();
        ReadPropertyFile prop = new ReadPropertyFile();

        DataController controller = new DataControllerImpl();

        try {
            int port = Integer.parseInt(prop.getValue("dfc.port")) ;
            String ip = prop.getValue("dfc.url");
            
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

