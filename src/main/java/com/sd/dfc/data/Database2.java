package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;
import java.io.IOException;

public class Database2 extends ArchiveManipulationImpl {

    private Map<BigInteger, byte[]> map = new HashMap<>();
    private static long count = 0;

    public  Database2() {
    }

    public Database2(String filename) {
        this.recoverData(filename);
    }

    // insere o vetor de bytes e retorna o id mapeado para o mesmo
    public long create(byte[] value) {
        map.put(BigInteger.valueOf(count), value);
        return count++;
    }

    private byte[] read(BigInteger id) {
        return map.get(id);
    }

    public Map<BigInteger, byte[]> readAll() {
        return map;
    }

    public byte[] update(BigInteger id, byte[] value) {
        return map.put(id, value);
    }

    public byte[] delete(BigInteger id) {
        return map.remove(id);
    }

    public void recoverData(String fileName) {

        try{
            FileReader file = new FileReader(fileName);
            BufferedReader readFile = new BufferedReader(file);
            String line;
            String[] splittedCommand;
            while((line = readFile.readLine())!=null){
                splittedCommand = line.split(" ");

                //lista com o comando subtraido do método e do nome do arquivo
                List<String> splittedList = Arrays.asList(splittedCommand);

                switch (splittedCommand[0]){
                    case "create":
                        this.create(String.join(" ", splittedList.subList(2, splittedList.size())).getBytes());
                        break;
                    case "update":
                        this.update(BigInteger.valueOf(Long.parseLong(splittedList.get(2))), String.join(" ", splittedList.subList(3,splittedList.size())).getBytes());
                        break;
                    case "delete":
                        this.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(2))));
                        break;
                }
            }

        }catch (Exception e){
            System.err.println(e.getStackTrace());
        }
    }
}
