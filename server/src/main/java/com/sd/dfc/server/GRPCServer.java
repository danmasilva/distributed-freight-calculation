package com.sd.dfc.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.sd.dfc.chord.*;
import com.sd.dfc.data.Database;
import com.sd.dfc.service.CepService;
import com.sd.dfc.service.PricingService;
import com.sd.dfc.service.TransportadoraService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {

	public static Database cepDatabase = null;
	public static Database transportadoraDatabase = null;
	public static Node m_node;

	public static void main(String[] args) throws IOException, InterruptedException {

		if (args.length < 3 || args[0].equals("-h")) {
			System.out.println("A aplicação deve ser executada com 3 ou 4 parâmetros:");
			System.out.println("- Porta na qual a aplicação será iniciada;");
			System.out.println("- Se for participar de anel existente, endereço de uma máq. no anel;");
			System.out.println("- Quantidade de servidores que participarão da DHT;");
			System.out.println("- ID desse servidor no anel (1 <= ID <= qtdServidores)");
			System.out.println("Exemplo: java -jar dht.jar 8081 192.168.100.12 5 3");
			System.exit(0);
		}

		final int PORT = Integer.parseInt(args[0]);
		cepDatabase = new Database("cep");
		transportadoraDatabase = new Database("transportadora");

		String local_ip;
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			local_ip = socket.getLocalAddress().getHostAddress();
		}

		// String local_ip = InetAddress.getLocalHost().getHostAddress();
		String contact_ip = null;
		int contact_port = -1;
		int qtd_nodes = -1;
		int node_id = -1;

		if (args.length == 3) { // primeiro nó a entrar no anel
			qtd_nodes = Integer.parseInt(args[1]);
			node_id = Integer.parseInt(args[2]);
		} else if (args.length == 5) { // nó participante de anel existente

			qtd_nodes = Integer.parseInt(args[1]);
			node_id = Integer.parseInt(args[2]);
			contact_ip = args[3];
			contact_port = Integer.parseInt(args[4]);
		}

		m_node = new Node(Helper.createSocketAddress(local_ip + ":" + args[0]), qtd_nodes, node_id, contact_ip,
				contact_port);

		Server server = ServerBuilder.forPort(PORT * 10).addService(new CepService())
				.addService(new TransportadoraService()).addService(new PricingService()).build();

		server.start();
		System.out.println("Server started at " + server.getPort());
		server.awaitTermination();
	}
}
