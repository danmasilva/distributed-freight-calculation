package com.sd.dfc.service;

import java.io.IOException;

import com.sd.dfc.controller.DataController;
import com.sd.dfc.controller.DataControllerImpl;
import com.sd.dfc.dao.CepDao;
import com.sd.dfc.dao.CepDaoImpl;
import com.sd.grpc.CepOuterClass.APIResponse;
import com.sd.grpc.CepOuterClass.CreateRequest;
import com.sd.grpc.CepOuterClass.DeleteRequest;
import com.sd.grpc.CepOuterClass.Empty;
import com.sd.grpc.CepOuterClass.UpdateRequest;
import com.sd.grpc.cepGrpc.cepImplBase;

import io.grpc.stub.StreamObserver;

public class CepService extends cepImplBase {

	CepDao cepDao = new CepDaoImpl();
	DataController dataController = new DataControllerImpl();

	@Override
	public void create(CreateRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("create cep request");
		String cepOrigem = request.getCep().getCepOrigem();
		String cepDestino = request.getCep().getCepDestino();

		StringBuilder query = new StringBuilder();

		query.append("create cep ").append(cepOrigem).append(" ").append(cepDestino);

		try {
			// realizando esse tipo de inserção para manter o código legado!!
			dataController.insert(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao inserir no banco");
			e.printStackTrace();
		}

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Cep cadastrado.").setCep(request.getCep());
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void update(UpdateRequest request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("update cep request");

		int id = request.getId();
		String cepOrigem = request.getCep().getCepOrigem();
		String cepDestino = request.getCep().getCepDestino();

		StringBuilder query = new StringBuilder();

		query.append("update cep ").append(id).append(" ").append(cepOrigem).append(" ").append(cepDestino);

		try {
			dataController.update(query.toString().split(" "));
		} catch (IOException e) {
			System.out.println("Falha ao atualizar banco");
			e.printStackTrace();
		}

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Cep atualizado.").setCep(request.getCep());
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void delete(DeleteRequest request, StreamObserver<APIResponse> responseObserver) {
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

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage("Cep removido.");
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();

	}

	@Override
	public void readall(Empty request, StreamObserver<APIResponse> responseObserver) {

		String query = "readall cep";

		String result = dataController.readAll(query.split(" "));

		APIResponse.Builder response = APIResponse.newBuilder();
		response.setResponseCode(201).setResponsemessage(result);
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();

	}

}
