package com.sd.dfc.data;

import com.sd.dfc.enums.FileEnum;

import java.io.*;

public class ArquiveManipulation {

    static Writer writer = null;
    //static private Map<BigInteger, byte[]> map = new HashMap<>();

    public ArquiveManipulation() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true)));
        } catch (Exception e) {
            System.out.println("Falha ao abrir arquivo");
        }
    }

    public void write(String text) throws IOException {
        //abre o file
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("data.txt", true)))) {
            writer.append(text).append("\n");
        }

        //fecha o file
    }

    public static void main(String[] args) throws IOException {
        ArquiveManipulation arq1 = new ArquiveManipulation();
        //arq1.write("daniel");
    }

}
