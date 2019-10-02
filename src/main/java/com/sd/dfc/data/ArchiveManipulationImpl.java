package com.sd.dfc.data;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;


public class ArchiveManipulationImpl implements ArchiveManipulation {

    public ArchiveManipulationImpl(){}

    static Writer writer = null;
    public String dataSource = "";
    //static private Map<BigInteger, byte[]> map = new HashMap<>();

    public ArchiveManipulationImpl(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void write(String text) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( dataSource, true)))) {
            writer.append(text).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        ArchiveManipulationImpl arq1 = new ArchiveManipulationImpl("teste.txt");
        //arq1.write("daniel");
    }

    @Override
    public String getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }


}
