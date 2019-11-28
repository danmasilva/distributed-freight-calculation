package com.sd.dfc.server;

import java.io.IOException;

import com.sd.dfc.data.Database2;
import com.sd.dfc.service.CepService;
import com.sd.dfc.service.TransportadoraService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {
	
	private static final int PORT = 12345;
    public static Database2 cepDatabase = null;
    public static Database2 transportadoraDatabase = null;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder
				.forPort(PORT)
				.addService( new CepService())
				.addService(new TransportadoraService())
				.build();
		
		cepDatabase = new Database2("cep.txt");
        transportadoraDatabase = new Database2("transportadora.txt");
		
		server.start();
		System.out.println("Server started at "+ server.getPort());
		server.awaitTermination();
	}
}
