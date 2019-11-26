package com.sd.dfc.dao;

import java.io.IOException;

public interface TransportadoraDao {
    void insert(String text) throws IOException;
    void update(int id, String text) throws IOException;
    void delete(int id, String text) throws IOException;
    void readAll();
}