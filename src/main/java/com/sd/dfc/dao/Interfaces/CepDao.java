package com.sd.dfc.dao.Interfaces;

import java.io.IOException;

public interface CepDao {
    public void insert(String text) throws IOException;
    public void update(int id, String text) throws IOException;
    public void delete(int id, String text) throws IOException;
    public void readAll();

}
