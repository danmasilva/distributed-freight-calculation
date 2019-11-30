package com.sd.dfc.service;

import java.io.IOException;
import java.util.List;

import com.sd.dfc.controller.DataControllerCepsImpl;
import com.sd.dfc.model.Ceps;
import com.sd.grpc.CepOuterClass.APICepResponse;
import com.sd.grpc.CepOuterClass.Cep;
import com.sd.grpc.CepOuterClass.CepResponse;
import com.sd.grpc.CepOuterClass.CreateRequest;
import com.sd.grpc.CepOuterClass.DeleteRequest;
import com.sd.grpc.CepOuterClass.Empty;
import com.sd.grpc.CepOuterClass.UpdateRequest;
import com.sd.grpc.cepGrpc.cepImplBase;

import io.grpc.stub.StreamObserver;

public class CepService extends cepImplBase{
	
	
	DataControllerCepsImpl dataController = new DataControllerCepsImpl();

	@Override
	public void create(CreateRequest request, StreamObserver<APICepResponse> responseObserver) {
		System.out.println("create cep request");
		long cepInicio = (long) request.getCep().getCepInicio();
		long cepFim = (long) request.getCep().getCepFim();

		StringBuilder query = new StringBuilder();

		query.append("create cep ").append(cepInicio).append(" ").append(cepFim);
		
		CepResponse.Builder builder = CepResponse.newBuilder();

		try {
			// realizando esse tipo de inserção para manter o código legado!!
			builder.setId((int) dataController.insert(query.toString().split(" ")));
			builder.setCepInicio(cepInicio);
			builder.setCepFim(cepFim);
		} catch (IOException e) {
			System.out.println("Falha ao inserir no banco");
			e.printStackTrace();
		}
		
		APICepResponse.Builder response = APICepResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Created").setCep(builder);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void update(UpdateRequest request, StreamObserver<APICepResponse> responseObserver) {
		System.out.println("update cep request");

		int id = request.getId();
		long cepInicio = request.getCep().getCepInicio();
		long cepFim = request.getCep().getCepFim();

		StringBuilder query = new StringBuilder();

		query.append("update cep ").append(id).append(" ").append(cepInicio).append(" ").append(cepFim);

		try {
			dataController.update(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao atualizar banco");
			e.printStackTrace();
		}
		
		CepResponse.Builder builder = CepResponse.newBuilder();
		builder.setId(id);
		builder.setCepInicio(cepInicio);
		builder.setCepFim(cepFim);

		APICepResponse.Builder response = APICepResponse.newBuilder();
		response.setResponseCode(200).setResponsemessage("Updated").setCep(builder);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void delete(DeleteRequest request, StreamObserver<APICepResponse> responseObserver) {
		System.out.println("delete cep request");
		int id = request.getId();

		StringBuilder query = new StringBuilder();

		query.append("delete cep ").append(id);

		try {
			dataController.delete(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao remover do banco");
			e.printStackTrace();
		}

		APICepResponse.Builder response = APICepResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Removed");
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void readall(Empty request, StreamObserver<CepResponse> responseObserver) {
		String query = "readall cep";
		List<Ceps> ceps = (List<Ceps>) dataController.readAll(query.split(" "));
		for (Ceps cep: ceps) {
			CepResponse.Builder builder = CepResponse.newBuilder();
			builder.setId(cep.getId());
			builder.setCepInicio(cep.getCepInicio());
			builder.setCepFim(cep.getCepFim());
			responseObserver.onNext(builder.build());
		}
		responseObserver.onCompleted();
	}
}
