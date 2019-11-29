package com.sd.dfc.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.sd.dfc.controller.DataControllerTransportadoraImpl;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.model.Transportadora;
import com.sd.dfc.server.GRPCServer;
import com.sd.grpc.TransportadoraOuterClass.APIResponse;
import com.sd.grpc.TransportadoraOuterClass.Cep;
import com.sd.grpc.TransportadoraOuterClass.CreateRequest;
import com.sd.grpc.TransportadoraOuterClass.DeleteRequest;
import com.sd.grpc.TransportadoraOuterClass.Empty;
import com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse;
import com.sd.grpc.TransportadoraOuterClass.UpdateRequest;
import com.sd.grpc.transportadoraGrpc.transportadoraImplBase;

import io.grpc.stub.StreamObserver;

public class TransportadoraService extends transportadoraImplBase {

	DataControllerTransportadoraImpl dataControllerTransportadora = new DataControllerTransportadoraImpl();

	@Override
	public void create(CreateRequest request, StreamObserver<APIResponse> responseObserver) {

		System.out.println("create transportadora request");

		String nome = request.getTransportadora().getNome();
		int idAbrangencia = request.getTransportadora().getIdAbrangencia();
		double peso = request.getTransportadora().getPeso();

		byte[] abrangencia = GRPCServer.cepDatabase.read(new BigInteger(String.valueOf(idAbrangencia)));

		if (abrangencia != null) {

			String[] cepValues = new String(abrangencia).split(" ");

			StringBuilder query = new StringBuilder();
			query.append("create transportadora ").append(nome).append(" ").append(idAbrangencia).append(" ")
					.append(peso);

			TransportadoraResponse.Builder transportadoraBuilder = TransportadoraResponse.newBuilder();
			Cep.Builder abrangenciaBuilder = Cep.newBuilder();
			abrangenciaBuilder.setCepInicio(Long.parseLong(cepValues[0]));
			abrangenciaBuilder.setCepFim(Long.parseLong(cepValues[1]));

			try {
				transportadoraBuilder.setId((int) dataControllerTransportadora.insert(query.toString().split(" ")));
				transportadoraBuilder.setNome(nome);
				transportadoraBuilder.setPeso(peso);
				transportadoraBuilder.setAbrangencia(abrangenciaBuilder);

			} catch (IOException e) {
				System.out.println("Falha ao inserir no banco");
				e.printStackTrace();
			}

			APIResponse.Builder response = APIResponse.newBuilder();
			response.setResponseCode(201).setResponsemessage("Transportadora cadastrada.")
					.setTransportadora(transportadoraBuilder);
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		} else {
			APIResponse.Builder response = APIResponse.newBuilder();
			response.setResponseCode(201)
					.setResponsemessage("Falha ao cadastrar transportadora. Abrangência informada existe?");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}
	}

	@Override
	public void update(UpdateRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("update transportadora request");

		int id = request.getId();
		String nome = request.getTransportadora().getNome();
		int idAbrangencia = request.getTransportadora().getIdAbrangencia();
		double peso = request.getTransportadora().getPeso();

		byte[] abrangencia = GRPCServer.cepDatabase.read(new BigInteger(String.valueOf(idAbrangencia)));

		if (abrangencia != null) {
			String[] cepValues = new String(abrangencia).split(" ");

			StringBuilder query = new StringBuilder();
			query.append("update transportadora ").append(id).append(" ").append(nome).append(" ").append(idAbrangencia)
					.append(" ").append(peso);

			TransportadoraResponse.Builder transportadoraBuilder = TransportadoraResponse.newBuilder();
			Cep.Builder abrangenciaBuilder = Cep.newBuilder();
			abrangenciaBuilder.setCepInicio(Long.parseLong(cepValues[0]));
			abrangenciaBuilder.setCepFim(Long.parseLong(cepValues[1]));

			try {
				dataControllerTransportadora.update(query.toString().split(" "));
				transportadoraBuilder.setId(id);
				transportadoraBuilder.setNome(nome);
				transportadoraBuilder.setPeso(peso);
				transportadoraBuilder.setAbrangencia(abrangenciaBuilder);
			} catch (IOException e) {
				System.out.println("Falha ao inserir no banco");
				e.printStackTrace();
			}

			APIResponse.Builder response = APIResponse.newBuilder();
			response.setResponseCode(201).setResponsemessage("Transportadora atualizada.")
					.setTransportadora(transportadoraBuilder);
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		} else {
			APIResponse.Builder response = APIResponse.newBuilder();
			response.setResponseCode(201)
					.setResponsemessage("Falha ao atualizar transportadora. Abrangência informada existe?");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}
	}

	@Override
	public void delete(DeleteRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("delete transportadora request");

		int id = request.getId();
		String query = "delete transportadora " + id;

		try {
			dataControllerTransportadora.delete(query.split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao deletar do banco");
			e.printStackTrace();
		}

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Transportadora deletada.");
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void readall(Empty request, StreamObserver<TransportadoraResponse> responseObserver) {
		String query = "readall transportadora";
		List<Transportadora> transportadoras = (List<Transportadora>) dataControllerTransportadora.readAll(query.split(" "));
		for (Transportadora t: transportadoras) {
			TransportadoraResponse.Builder tBuilder = TransportadoraResponse.newBuilder();
			Cep.Builder cBuilder = Cep.newBuilder();
			Ceps abrangencia = t.getAbrangencia();
			cBuilder.setCepInicio(abrangencia.getCepInicio());
			cBuilder.setCepFim(abrangencia.getCepFim());
			tBuilder.setId((int) t.getId());
			tBuilder.setAbrangencia(cBuilder);
			tBuilder.setNome(t.getNome());
			
			responseObserver.onNext(tBuilder.build());
		}
		responseObserver.onCompleted();
	}

}
