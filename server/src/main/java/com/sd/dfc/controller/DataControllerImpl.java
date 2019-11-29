package com.sd.dfc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.sd.dfc.data.ArchiveManipulation;
import com.sd.dfc.data.ArchiveManipulationImpl;
import com.sd.dfc.data.Commands;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.model.Transportadora;
import com.sd.dfc.server.GRPCServer;

//controlador genérico que identifica na chamada do método o database que deverá modificar.
public class DataControllerImpl implements DataController, DataReadStringController{
    private final String cep = "cep.txt";
    private final String transportadora = "transportadora.txt";

    private ArchiveManipulation cepArchive = new ArchiveManipulationImpl(this.cep);
    private ArchiveManipulation transportadoraArchive = new ArchiveManipulationImpl(this.transportadora);

    @Override
    public long insert(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
        long createdId;

        if(splittedList.get(1).equals("cep")){
        	createdId = GRPCServer.cepDatabase.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
            cepArchive.write(String.join(" ", splittedList));

            return createdId;
        }else if (splittedList.get(1).equals("transportadora")){
        	createdId = GRPCServer.transportadoraDatabase.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
            transportadoraArchive.write(String.join(" ", splittedList));
            return createdId;
        }
        return -1;
    }

    @Override
    public String readAll(String[] splittedMessage) {
        Map<BigInteger, byte[]> map;
        StringBuilder result = new StringBuilder();

        if(splittedMessage[1].equals("cep")){
            map = GRPCServer.cepDatabase.readAll();
            for (Map.Entry<BigInteger, byte[]> entry : map.entrySet()) {
                String[] values = new String(entry.getValue()).split(" ");
                Ceps ceps = new Ceps(Long.parseLong(entry.getKey().toString()),Long.parseLong(values[0]), Long.parseLong(values[1]));
                result.append(ceps.getId()).append(": de ").append(ceps.getCepInicio()).append(" até ").append(ceps.getCepFim()).append(", ");
            }
        }
        else if(splittedMessage[1].equals("transportadora")){

            map = GRPCServer.transportadoraDatabase.readAll();
            for (Map.Entry<BigInteger, byte[]> entry : map.entrySet()) {
                String[] transportadoraValues = new String(entry.getValue()).split(" ");
                String[] cepValues = new String(
                        GRPCServer.cepDatabase.read(
                                BigInteger.valueOf(
                                        Long.parseLong(transportadoraValues[1]))))
                        .split(" ");

                Ceps ceps = new Ceps();
                ceps.setId(Long.parseLong(transportadoraValues[1]));
                ceps.setCepInicio(Long.parseLong(cepValues[0]));
                ceps.setCepFim(Long.parseLong(cepValues[1]));


                Transportadora transportadora = new Transportadora(
                        Long.parseLong(entry.getKey().toString()),
                        transportadoraValues[0],ceps,
                        Double.parseDouble(transportadoraValues[2]));


                result.append(transportadora.getId())
                        .append(": ")
                        .append(transportadora.getNome())
                        .append(", peso ").append(transportadora.getPeso()).append(" e abrangência de ")
                        .append(transportadora.getAbrangencia().getCepInicio()).append(" a ")
                        .append(transportadora.getAbrangencia().getCepFim()).append(", ");
                //result.append(ceps.getId()).append(": de ").append(ceps.getCepInicio()).append(" até ").append(ceps.getCepFim()).append(", ");
            }
        }else{
            map = new HashMap<>();
        }


        return result.toString();
    }

    @Override
    public byte[] update(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));

        if(splittedMessage[1].equals("cep")){
            cepArchive.write(String.join(" ", splittedList));
            return GRPCServer.cepDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
                    String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
        }else if (splittedMessage[1].equals("transportadora")){
            transportadoraArchive.write(String.join(" ", splittedList));
            return GRPCServer.transportadoraDatabase.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))),
                    String.join(" ", splittedList.subList(3, splittedList.size())).getBytes());
        }
        return null;
    }

    @Override
    public byte[] delete(String[] splittedMessage) throws IOException {
        List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
        if(splittedMessage[1].equals("cep")){
            cepArchive.write(String.join(" ", splittedList));
            return GRPCServer.cepDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
        }else if (splittedMessage[1].equals("transportadora")){
            transportadoraArchive.write(String.join(" ", splittedList));
            return  GRPCServer.transportadoraDatabase.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
        }
        return null;
    }
    
    @Override
    public String validCommand(String input) {
        List<String> validCommands;

        Object[] validCommandsObject = Stream
                .of(Commands.INSERT.getAllOps(), Commands.DELETE.getAllOps(), Commands.UPDATE.getAllOps(), Commands.READ_ALL.getAllOps())
                .flatMap(Stream::of)
                .toArray();
        validCommands = Arrays.asList(Arrays.copyOf(validCommandsObject, validCommandsObject.length, String[].class));

        if (validCommands.stream().anyMatch(str -> str.trim().equals(input.split(" ")[0]))
        ) {
            String[] splittedCommand = input.split(" ");
            List<String> splittedList = new ArrayList<>(Arrays.asList(splittedCommand));

            // command has sufficient parameters?
            if(Arrays.stream(Commands.INSERT.getAllOps()).anyMatch(str -> str.trim().equals(splittedList.get(0).toLowerCase()))){
                if (splittedList.size() == 4 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))){
                    //retorna qual database comando atuará
                    return splittedCommand[1];
                }
            }
            else if(Arrays.stream(Commands.READ_ALL.getAllOps()).anyMatch(str -> str.trim().equals(splittedList.get(0).toLowerCase()))){
                if(splittedList.size() == 2 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))){
                    //retorna qual database comando atuará
                    return splittedCommand[1];
                }
            }
            else if(Arrays.stream(Commands.UPDATE.getAllOps()).anyMatch(str -> str.trim().equals(splittedList.get(0).toLowerCase()))){
                if (!(splittedList.size() == 4 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))))
                    return null;

                // o segundo parâmetro deve poder ser convertido para float
                try {
                    Long.parseLong(splittedCommand[2]);
                } catch (Exception e) {
                    return null;
                }

                return splittedCommand[1];
            }
            else if(Arrays.stream(Commands.DELETE.getAllOps()).anyMatch(str -> str.trim().equals(splittedList.get(0).toLowerCase()))){
                if (!(splittedList.size() == 3 && (splittedCommand[1].equals("cep")||splittedCommand[1].equals("transportadora"))))
                    return null;
                // o segundo parâmetro deve poder ser convertido para float
                try {
                    Long.parseLong(splittedCommand[2]);
                } catch (Exception e) {
                    return null;
                }

                return splittedCommand[1];
            }
            else return null;

        }
        return null;
    }

    //TODO: verificar se o out(printwriter) funciona sendo recebido como argumento. senao, voltar para o echothread
    @Override
    public boolean putData(PrintWriter out, String data) throws IOException {
        String[] splittedMessage = data.split(" ");

        byte[] response;

        if (Arrays.stream(Commands.INSERT.getAllOps()).anyMatch(str -> str.trim().equals(splittedMessage[0].toLowerCase()))) {
            if (this.insert(splittedMessage)!=-1) {
                out.println("Message inserted: " + String.join(" ", splittedMessage));
            } else {
                out.println("Fail on insert message");
            }
        } else if (Arrays.stream(Commands.UPDATE.getAllOps()).anyMatch(str -> str.trim().equals(splittedMessage[0].toLowerCase()))) {
            response = this.update(splittedMessage);
            if (response != null) {
                out.println("Previous message: " + new String(response) + ". Message updated!");
            } else {
                out.println("Fail on update message.");
            }
        } else if (Arrays.stream(Commands.DELETE.getAllOps()).anyMatch(str -> str.trim().equals(splittedMessage[0].toLowerCase()))) {
            response = this.delete(splittedMessage);
            if (response != null) {
                out.println("Message removed: " + new String(response));
            } else {
                out.println("Fail on removing item.");
            }
        } else if (Arrays.stream(Commands.READ_ALL.getAllOps()).anyMatch(str -> str.trim().equals(splittedMessage[0].toLowerCase()))) {
            String returnedData = this.readAll(splittedMessage);

            // remove the last comma
            if (returnedData.length() != 0) {
                out.println(returnedData.substring(0, returnedData.length() - 2));
            } else {
                out.println("Database vazio");
            }
        }
        return true;
    }
}
