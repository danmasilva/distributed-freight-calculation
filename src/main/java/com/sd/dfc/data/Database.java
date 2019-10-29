package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Database extends ArchiveManipulationImpl {

    private static Map<BigInteger, byte[]> map = new HashMap<>();
    private static long count = 0;

    public  Database() {
    }

    public Database(String filename) {
        recuperaDados(filename);
    }

    public static Map<BigInteger, byte[]> getMap() {
        return map;
    }

    public static void setMap(Map<BigInteger, byte[]> map) {
        Database.map = map;
    }

    // insere o vetor de bytes e retorna o id mapeado para o mesmo
    public synchronized long create(byte[] value) {
        getMap().put(BigInteger.valueOf(getCount()), value);
        count++;
        return count;
    }

    private synchronized byte[] read(BigInteger id) {
        return getMap().get(id);
    }

    public  Map<BigInteger, byte[]> readAll() {
        return getMap();
    }

    public  synchronized byte[] update(BigInteger id, byte[] value) {
        return getMap().put(id, value);
    }

    public  synchronized byte[] delete(BigInteger id) {
        return getMap().remove(id);
    }

    static  private void recuperaDados(String fileName) {
        Database database = new Database();
        int aux = 0;

        StringBuilder lerMetodo = new StringBuilder();
        StringBuilder lerValor = new StringBuilder();
        String lerId = "";
        try {
            FileReader dados = new FileReader(fileName);
            BufferedReader lerDados = new BufferedReader(dados);

            String linha = lerDados.readLine(); // Le a primeira linha

            while (linha != null) {
                for (int i = 0; i < linha.length(); i++) {
                    char c = linha.charAt(i);
                    aux = i; // para depois começar onde parou
                    if (c != ' ') // lendo o metodo
                    {
                        lerMetodo.append(c);
                    } else {
                        break;
                    }
                }

                aux++; // tira o espaço pra começar no caracter
                switch (lerMetodo.toString()) {
                    case "create":
                        for (int i = aux; i < linha.length(); i++) {
                            char c = linha.charAt(i); // le valor do parametro
                            lerValor.append(c); // adicionando um caracter na string
                        }
                        database.create(lerValor.toString().getBytes());
                        break;
                    case "update":
                        for (int i = aux; i < linha.length(); i++) {
                            char c = linha.charAt(i);
                            if (c != ' ') {
                                c = linha.charAt(i); // lendo o id
                                lerId = lerId + c;
                            } else {
                                break;
                            }
                        }
                        aux++; // tira o espaço pra começar no caracter

                        for (int i = aux; i < linha.length(); i++) {
                            char c = linha.charAt(i); // lendo o parametro
                            lerValor.append(c);
                        }

                        database.update(BigInteger.valueOf(Integer.parseInt(lerId)), lerValor.toString().getBytes());
                        break;
                    case "delete":
                        for (int i = aux; i < linha.length(); i++) {
                            char c = linha.charAt(i); // le o id
                            lerId = lerId + c;
                        }
                        database.delete(BigInteger.valueOf(Integer.parseInt(lerId)));
                        break;
                }

                aux = 0;
                lerMetodo = new StringBuilder();
                lerValor = new StringBuilder();
                lerId = "";
                linha = lerDados.readLine();
            }

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    /**
     * @return the count
     */
    public static long getCount() {
        return count;
    }
    public static void setCount(long value) {
        Database.count = value;
    }


}
