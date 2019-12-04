package com.sd.dfc.server;

import java.io.IOException;

import com.sd.dfc.config.ReadPropertyFile;
import com.sd.dfc.data.Database;
import com.sd.dfc.service.CepService;
import com.sd.dfc.service.TransportadoraService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {
	
    public static Database cepDatabase = null;
    public static Database transportadoraDatabase = null;
    
    
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ReadPropertyFile prop = new ReadPropertyFile();
		//se foi passada porta como parâmetro, iniciar servidor nessa porta.
		//senao, iniciar na porta padrao inserida no properties.
		final int PORT = args[0]!=null ? Integer.parseInt(args[0]) : Integer.parseInt(prop.getValue("dfc.server.port")); 

		cepDatabase = new Database("cep");
		transportadoraDatabase = new Database("transportadora");
		
		Server server = ServerBuilder
				.forPort(PORT)
				.addService( new CepService())
				.addService(new TransportadoraService())
				.build();
		
		server.start();
		System.out.println("Server started at "+ server.getPort());
		server.awaitTermination();
	}
}
