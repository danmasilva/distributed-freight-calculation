package com.sd.dfc.dao.impl;

import com.sd.dfc.dao.Interfaces.CepDao;
import com.sd.dfc.data.ArchiveManipulation;
import com.sd.dfc.data.ArchiveManipulationImpl;

import java.io.IOException;

public class CepDaoImpl implements CepDao {
    private  String DATASOURCE = "CEP.txt";

    ArchiveManipulation handler =  new ArchiveManipulationImpl(DATASOURCE);

    @Override
    public void insert(String text) throws IOException {
        handler.write(text);
    }

    @Override
    public void update(int id, String text) throws IOException {
        StringBuilder command = new StringBuilder();
        command.append(String.valueOf(id)).append(text);
        handler.write(command.toString());
    }

    @Override
    public void delete(int id, String text) throws IOException {
        StringBuilder command = new StringBuilder();
        command.append(String.valueOf(id)).append(text);
        handler.write(command.toString());
    }

    @Override
    public void readAll() {

    }
}
