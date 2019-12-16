package com.sd.dfc.server;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.sd.dfc.controller.DataControllerImpl;

import io.atomix.cluster.Node;
import io.atomix.cluster.discovery.BootstrapDiscoveryProvider;
import io.atomix.core.Atomix;
import io.atomix.core.AtomixBuilder;
import io.atomix.core.map.DistributedMap;
import io.atomix.core.profile.ConsensusProfile;
import io.atomix.utils.net.Address;

public class ServerThread {

	public static DistributedMap<BigInteger, byte[]> cepDatabase = null;
	public static DistributedMap<BigInteger, byte[]> transportadoraDatabase = null;

	public static final String INSERT = "insert";
	public static final String CREATE = "create";
	public static final String INSERIR = "inserir";
	public static final String READ_ALL = "readall";
	public static final String LER_TODOS = "lertodos";
	public static final String UPDATE = "update";
	public static final String CHANGE = "change";
	public static final String ALTERAR = "alterar";
	public static final String DELETE = "delete";
	public static final String DELETAR = "deletar";

	public static void main(String[] args) {

		int myId = Integer.parseInt(args[0]);
		List<Address> addresses = new LinkedList<>();

		for (int i = 1; i < args.length; i += 2) {
			Address address = new Address(args[i], Integer.parseInt(args[i + 1]));
			addresses.add(address);
		}

		AtomixBuilder builder = Atomix.builder();
		builder.withClusterId("member1").withAddress("192.168.100.22").build();

		Atomix atomix = builder.withMemberId("member-" + myId).withAddress(addresses.get(myId))
				.withMembershipProvider(BootstrapDiscoveryProvider.builder()
						.withNodes(Node.builder().withId("member-0").withAddress(addresses.get(0)).build(),
								Node.builder().withId("member-1").withAddress(addresses.get(1)).build(),
								Node.builder().withId("member-2").withAddress(addresses.get(2)).build())
						.build())
				.withProfiles(ConsensusProfile.builder().withDataPath("/tmp/member-" + myId)
						.withMembers("member-1", "member-2", "member-3").build())
				.build();

		atomix.start().join();
		System.out.println("oi");

		atomix.getMembershipService().addListener(event -> {
			switch (event.type()) {
			case MEMBER_ADDED:
				System.out.println(event.subject().id() + " joined the cluster");
				break;
			case MEMBER_REMOVED:
				System.out.println(event.subject().id() + " left the cluster");
				break;
			default:
				break;
			}
		});

		cepDatabase = atomix.<BigInteger, byte[]>mapBuilder("cepDatabase")
				.withCacheEnabled().build();

		transportadoraDatabase = atomix.<BigInteger, byte[]>mapBuilder("transportadoraDatabase")
				.withCacheEnabled().build();
		
		DataControllerImpl cepController = new DataControllerImpl("cep", cepDatabase);
		DataControllerImpl transportadoraController = new DataControllerImpl("transportadora", transportadoraDatabase);
		
		System.out.println("Databases ready");
		
		atomix.getCommunicationService().subscribe("message", message -> {
			//verifica se input é valido, caso positivo guarda o nome do database de destino
			String destiny = cepController.validCommand((String) message);
			String result = null;
			if(destiny.equals("cep")) {
				cepController.updateDatabase(cepDatabase);
				result = cepController.putData((String) message);
			} else if(destiny.equals("transportadora")) {
				transportadoraController.updateDatabase(transportadoraDatabase);
				result = transportadoraController.putData((String) message);
			}
			return CompletableFuture.completedFuture(result);
		});
	}

}
