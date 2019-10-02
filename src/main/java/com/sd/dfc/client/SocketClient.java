package com.sd.dfc.client;

import com.sd.dfc.config.ReadPropertyFile;
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
    public static final String READ_ALL = "readAll";
    public static final String LER_TODOS = "lerTodos";
    public static final String UPDATE = "update";
    public static final String CHANGE = "change";
    public static final String ALTERAR = "alterar";
    public static final String DELETE = "delete";
    public static final String DELETAR = "deletar";

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Database data;
    private static ArchiveManipulationImpl arq = new ArchiveManipulationImpl();

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

    private static boolean validCommand(String input) {
        List<String> validCommands = Arrays.asList(
                // create
                SocketClient.INSERT, SocketClient.CREATE, SocketClient.INSERIR,
                // read all
                SocketClient.READ_ALL, SocketClient.LER_TODOS,
                // update
                SocketClient.UPDATE, SocketClient.CHANGE, SocketClient.ALTERAR,
                // delete
                SocketClient.DELETE, SocketClient.DELETAR);

        if (validCommands.stream().anyMatch(str -> str.trim().equals(input.split(" ")[0]))
        ) {
            String[] splittedCommand = input.split(" ");
            List<String> splittedList = new ArrayList<>(Arrays.asList(splittedCommand));
            // command has sufficient parameters?
            switch (splittedCommand[0]) {
                case SocketClient.INSERT:
                case SocketClient.CREATE:
                case SocketClient.INSERIR:
                    return (splittedList.size() == 2);
                case SocketClient.READ_ALL:
                case SocketClient.LER_TODOS:
                    return splittedList.size() == 1;
                case SocketClient.ALTERAR:
                case SocketClient.CHANGE:
                case SocketClient.UPDATE:
                    // o primeiro parâmetro deve poder ser convertido para float
                    try {
                        Long.parseLong(splittedCommand[1]);
                    } catch (Exception e) {
                        return false;
                    }
                    // o segundo parâmetro deve conter algo para substituir o conte�do anterior
                    return splittedList.size() > 2;
                case SocketClient.DELETE:
                case SocketClient.DELETAR:
                    // o primeiro parâmetro deve poder ser convertido para float
                    try {
                        Long.parseLong(splittedCommand[1]);
                    } catch (Exception e) {
                        return false;
                    }
                    // o segundo parâmetro deve conter nada
                    return splittedList.size() == 2;

                default:
                    return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SocketClient server = new SocketClient();
        ReadPropertyFile prop = new ReadPropertyFile();

        try {
            int port = Integer.parseInt(prop.getValue("dfc.port")) ;
            String ip = prop.getValue("dfc.url");
            
            server.startConnection(ip , port);
            Menu menu = new Menu();
            System.out.println(menu.presentMenu());

            //BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
            Scanner s = new Scanner(System.in);
            String text;

            long startServiceTime; // utilizado para o timeout
            while (true) {

                text = s.nextLine();

                if (!(text).equals("sair") && !(text).equals("quit") && !(text).equals("exit")) {

                    if (SocketClient.validCommand(text)) {
                        System.out.println(server.sendMessage(text));

                        if(!text.equals("readAll"))
                            arq.write( text);

                    } else {
                        System.out.println("This is not a valid command.");
                    }

                    //s = new BufferedReader(new InputStreamReader(System.in));
                } else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

