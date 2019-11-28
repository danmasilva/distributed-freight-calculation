package com.sd.dfc.service;

import java.io.IOException;

import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerImpl;
import com.sd.grpc.TransportadoraOuterClass.APIResponse;
import com.sd.grpc.TransportadoraOuterClass.CreateRequest;
import com.sd.grpc.TransportadoraOuterClass.DeleteRequest;
import com.sd.grpc.TransportadoraOuterClass.Empty;
import com.sd.grpc.TransportadoraOuterClass.UpdateRequest;
import com.sd.grpc.transportadoraGrpc.transportadoraImplBase;

import io.grpc.stub.StreamObserver;

public class TransportadoraService extends transportadoraImplBase{
	
	DataController dataController = new DataControllerImpl();

	@Override
	public void create(CreateRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("create transportadora request");
		
		String nome = request.getTransportadora().getNome();
		int idAbrangencia = request.getTransportadora().getIdAbrangencia();
		double peso = request.getTransportadora().getPeso();
		
		StringBuilder query = new StringBuilder();
		query
			.append("create transportadora ")
			.append(nome)
			.append(" ")
			.append(idAbrangencia)
			.append(" ")
			.append(peso);
		
		try {
			dataController.insert(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao inserir no banco");
			e.printStackTrace();
		}
		
		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Transportadora cadastrada.").setTransportadora(request.getTransportadora());
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void update(UpdateRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("update transportadora request");
		
		int id = request.getId();
		String nome = request.getTransportadora().getNome();
		int idAbrangencia = request.getTransportadora().getIdAbrangencia();
		double peso = request.getTransportadora().getPeso();
		
		StringBuilder query = new StringBuilder();
		query
			.append("update transportadora ")
			.append(id)
			.append(" ")
			.append(nome)
			.append(" ")
			.append(idAbrangencia)
			.append(" ")
			.append(peso);
		
		try {
			dataController.update(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao inserir no banco");
			e.printStackTrace();
		}
		
		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Transportadora atualizada.").setTransportadora(request.getTransportadora());
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void delete(DeleteRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("delete transportadora request");
		
		int id = request.getId();
		String query = "delete transportadora " + id;
		
		try {
			dataController.delete(query.split(" "));
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
	public void readall(Empty request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("readall transportadora request");
		
		String query = "readall transportadora";
		String result = dataController.readAll(query.split(" "));

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage(result);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
		
	}
	
}
