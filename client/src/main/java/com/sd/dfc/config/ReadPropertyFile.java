package com.sd.dfc.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
    Properties prop = new Properties();
    //FileInputStream fis;
    InputStream is;

    public ReadPropertyFile(){
        try {
            //fis = new FileInputStream("src/resources/application.properties");
        	is = getClass().getClassLoader().getResourceAsStream("application.properties");
            prop.load(is);
        } catch (FileNotFoundException e) {
            System.err.println("Falha ao encontrar o arquivo.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Falha ao carregar o arquivo.");
            e.printStackTrace();
        }
    }

    public String getValue(String key){
        return prop.getProperty(key);
    }

}
