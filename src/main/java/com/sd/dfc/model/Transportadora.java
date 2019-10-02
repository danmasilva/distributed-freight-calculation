package com.sd.dfc.model;

import lombok.Data;

import java.util.List;

@Data
public class Transportadora {
    int id;
    String nome;
    List<Cep[]> abrangencia;
    double peso;
}
