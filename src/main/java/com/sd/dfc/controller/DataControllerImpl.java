package com.sd.dfc.controller;

import com.sd.dfc.client.SocketClient;
import com.sd.dfc.data.ArchiveManipulation;
import com.sd.dfc.data.ArchiveManipulationImpl;
import com.sd.dfc.server.ServerThread;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class DataControllerImpl implements DataController{
    private final String cep = "cep.txt";
    private final String transportadora = "transportadora.txt";

    private ArchiveManipulation cepArchive = new ArchiveManipulationImpl(this.cep);
    private ArchiveManipulation transportadoraArchive = new ArchiveManipulationImpl(this.transportadora);

    @Override
    public boolean insert(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));

        if(splittedList.get(1).equals("cep")){
            ServerThread.cepDatabase.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
            cepArchive.write(String.join(" ", splittedList));

            return true;
        }else if (splittedList.get(1).equals("transportadora")){
            ServerThread.transportadoraDatabase.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
            transportadoraArchive.write(String.join(" ", splittedList));
            return true;
        }
        return false;
    }

    @Override
    public String readAll(String[] splittedMessage) {
        Map<BigInteger, byte[]> map;
        if(splittedMessage[1].equals("cep")){
            map = ServerThread.cepDatabase.readAll();
        }else if(splittedMessage[1].equals("transportadora")){
            map = ServerThread.transportadoraDatabase.readAll();
        }else{
            map = new HashMap<>();
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<BigInteger, byte[]> entry : map.entrySet()) {
            result.append(entry.getKey()).append(": ").append(new String(entry.getValue())).append(", ");
        }
        return result.toString();
    }

    @Override
    public byte[] update(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));

        if(splittedMessage[1].equals("cep")){
            cepArchive.write(String.join(" ", splittedList));
            return ServerThread.cepDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
                    String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
        }else if (splittedMessage[1].equals("transportadora")){
            transportadoraArchive.write(String.join(" ", splittedList));
            return ServerThread.transportadoraDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
                    String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
        }
        return null;
    }

    @Override
    public byte[] delete(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
        if(splittedMessage[1].equals("cep")){
            cepArchive.write(String.join(" ", splittedList));
            return ServerThread.cepDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
        }else if (splittedMessage[1].equals("transportadora")){
            transportadoraArchive.write(String.join(" ", splittedList));
            return  ServerThread.transportadoraDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
        }
        return null;
    }

    @Override
    public String validCommand(String input) {
        List<String> validCommands;
        validCommands = Arrays.asList(
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
            switch (splittedList.get(0).toLowerCase()) {
                case SocketClient.INSERT:
                case SocketClient.CREATE:
                case SocketClient.INSERIR:
                    if (splittedList.size() == 3 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))){
                        //retorna qual database comando atuará
                        return splittedCommand[1];
                    }
                case SocketClient.READ_ALL:
                case SocketClient.LER_TODOS:
                    if(splittedList.size() == 2 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))){
                        //retorna qual database comando atuará
                        return splittedCommand[1];
                    }
                case SocketClient.ALTERAR:
                case SocketClient.CHANGE:
                case SocketClient.UPDATE:

                    if (!(splittedList.size() == 4 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))))
                        return null;

                    // o segundo parâmetro deve poder ser convertido para float
                    try {
                        Long.parseLong(splittedCommand[2]);
                    } catch (Exception e) {
                        return null;
                    }

                    return splittedCommand[1];

                case SocketClient.DELETE:
                case SocketClient.DELETAR:
                    if (!(splittedList.size() == 3 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))))
                        return null;
                    // o segundo parâmetro deve poder ser convertido para float
                    try {
                        Long.parseLong(splittedCommand[2]);
                    } catch (Exception e) {
                        return null;
                    }

                    return splittedCommand[1];

                default:
                    return null;
            }
        }
        return null;
    }

    //TODO: verificar se o out(printwriter) funciona sendo recebido como argumento. senao, voltar para o echothread
    @Override
    public boolean putData(PrintWriter out, String data) throws IOException {
        String[] splittedMessage = data.split(" ");

        byte[] response;
        switch (splittedMessage[0]) {
            case SocketClient.INSERT:
            case SocketClient.CREATE:
            case SocketClient.INSERIR:
                if(this.insert(splittedMessage)){
                    out.println("Message inserted: " + String.join(" ", splittedMessage));
                }else{
                    out.println("Deu errado");
                }
                break;
            case SocketClient.CHANGE:
            case SocketClient.ALTERAR:
            case SocketClient.UPDATE:
                response = this.update(splittedMessage);
                if (response != null) {
                    out.println("Previous message: " + new String(response)+ ". Message updated!");
                } else {
                    out.println("Fail on update message.");
                }
                break;
            case SocketClient.DELETAR:
            case SocketClient.DELETE:
                response = this.delete(splittedMessage);
                if (response != null) {
                    out.println("Message removed: " + new String(response));
                } else {
                    out.println("Fail on removing item.");
                }
                break;
            case SocketClient.READ_ALL:
            case SocketClient.LER_TODOS:
                String returnedData = this.readAll(splittedMessage);

                // remove the last comma
                if (returnedData.length() != 0) {
                    out.println(returnedData.substring(0, returnedData.length()-2));
                } else
                {
                    out.println("Database vazio");
                }

                break;

            default:
                break;
        }
        return true;
    }


}
