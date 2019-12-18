package com.sd.dfc.service;

import java.math.BigInteger;
import java.util.List;

import com.sd.dfc.controller.DataControllerTransportadoraImpl;
import com.sd.dfc.model.Ceps;
import com.sd.dfc.model.Transportadora;
import com.sd.dfc.server.GRPCServer;
import com.sd.grpc.TransportadoraOuterClass.APITransportadoraResponse;
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
	public void create(CreateRequest request, StreamObserver<APITransportadoraResponse> responseObserver) {

		System.out.println("create transportadora request");

		String nome = request.getTransportadora().getNome();
		String cepChave = request.getTransportadora().getCepChave();
		double peso = request.getTransportadora().getPeso();

		byte[] abrangencia = GRPCServer.cepDatabase.read(cepChave);

		if (nome.length() == 0 || peso == 0.0 || abrangencia == null) {
			APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
			response.setResponseCode(400).setResponsemessage("Bad Request");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
			return;
		}

		String[] cepValues = new String(abrangencia).split(" ");

		StringBuilder query = new StringBuilder();
		query.append("create transportadora ").append(nome).append(" ").append(cepChave).append(" ").append(peso);

		TransportadoraResponse.Builder transportadoraBuilder = TransportadoraResponse.newBuilder();
		Cep.Builder abrangenciaBuilder = Cep.newBuilder();
		abrangenciaBuilder.setChave(cepChave);
		abrangenciaBuilder.setCepInicio(Long.parseLong(cepValues[0]));
		abrangenciaBuilder.setCepFim(Long.parseLong(cepValues[1]));

		try {
			transportadoraBuilder.setId( dataControllerTransportadora.insert(query.toString().split(" ")));
			transportadoraBuilder.setNome(nome);
			transportadoraBuilder.setPeso(peso);
			transportadoraBuilder.setAbrangencia(abrangenciaBuilder);

		} catch (Exception e) {
			APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
			response.setResponseCode(500).setResponsemessage("Internal Server Error");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
			return;
		}

		APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Created").setTransportadora(transportadoraBuilder);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void update(UpdateRequest request, StreamObserver<APITransportadoraResponse> responseObserver) {
		System.out.println("update transportadora request");

		String id = request.getId();
		String nome = request.getTransportadora().getNome();
		String cepChave = request.getTransportadora().getCepChave();
		double peso = request.getTransportadora().getPeso();

		byte[] abrangencia = GRPCServer.cepDatabase.read(cepChave);

		if (nome.length() == 0 || cepChave == null  || cepChave.isEmpty() || peso == 0.0 || abrangencia == null) {
			APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
			response.setResponseCode(400).setResponsemessage("Bad Request");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
			return;
		}

		String[] cepValues = new String(abrangencia).split(" ");

		StringBuilder query = new StringBuilder();
		query.append("update transportadora ").append(id).append(" ").append(nome).append(" ").append(cepChave)
				.append(" ").append(peso);

		TransportadoraResponse.Builder transportadoraBuilder = TransportadoraResponse.newBuilder();
		Cep.Builder abrangenciaBuilder = Cep.newBuilder();
		abrangenciaBuilder.setChave(cepChave);
		abrangenciaBuilder.setCepInicio(Long.parseLong(cepValues[0]));
		abrangenciaBuilder.setCepFim(Long.parseLong(cepValues[1]));

		try {
			dataControllerTransportadora.update(query.toString().split(" "));
			transportadoraBuilder.setId(id);
			transportadoraBuilder.setNome(nome);
			transportadoraBuilder.setPeso(peso);
			transportadoraBuilder.setAbrangencia(abrangenciaBuilder);
		} catch (Exception e) {
			APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
			response.setResponseCode(500).setResponsemessage("Internal Server Error");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}

		APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Updated")
				.setTransportadora(transportadoraBuilder);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void delete(DeleteRequest request, StreamObserver<APITransportadoraResponse> responseObserver) {
		System.out.println("delete transportadora request");

		String id = request.getId();
		String query = "delete transportadora " + id;

		try {
			dataControllerTransportadora.delete(query.split(" "));
		} catch (Exception e) {
			APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
			response.setResponseCode(500).setResponsemessage("Internal Server Error");
			responseObserver.onNext(response.build());
			responseObserver.onCompleted();
		}

		APITransportadoraResponse.Builder response = APITransportadoraResponse.newBuilder();
		response.setResponseCode(204).setResponsemessage("No Content");
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readall(Empty request, StreamObserver<TransportadoraResponse> responseObserver) {
		String query = "readall transportadora";
		List<Transportadora> transportadoras = (List<Transportadora>) dataControllerTransportadora
				.readAll(query.split(" "));
		for (Transportadora t : transportadoras) {
			TransportadoraResponse.Builder tBuilder = TransportadoraResponse.newBuilder();
			Cep.Builder cBuilder = Cep.newBuilder();
			Ceps abrangencia = t.getAbrangencia();
			cBuilder.setCepInicio(abrangencia.getCepInicio());
			cBuilder.setCepFim(abrangencia.getCepFim());
			tBuilder.setId( t.getId());
			tBuilder.setAbrangencia(cBuilder);
			tBuilder.setNome(t.getNome());

			responseObserver.onNext(tBuilder.build());
		}
		responseObserver.onCompleted();
	}

}
