package com.sd.dfc.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Database extends ArchiveManipulationImpl {

    private static Map<BigInteger, byte[]> map = new HashMap<>();
    private static long count = 0;

    public  Database() {
    }

    public Database(String filename) {
        recuperaDados(filename);
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

    static private void recuperaDados(String fileName) {
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
//            writer.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void main(String[] args) {
        Database database = new Database();
        System.out.println(database.create("daniel marques".getBytes()));
        System.out.println(database.create("juliana alves".getBytes()));
        System.out.println(database.create("giovana marques".getBytes()));
        System.out.println(database.create("luiz antonio".getBytes()));

        System.out.println(database.readAll());
        // Iterator iterator = database.readAll().entrySet().iterator();

        for (int i = 0; i < Database.map.size(); i++) {
            System.out.println(new String(database.read(BigInteger.valueOf(i))));
        }
    }
}
