package com.sd.dfc.controller;

import java.util.Iterator;

import com.sd.grpc.CepOuterClass.APICepResponse;
import com.sd.grpc.CepOuterClass.Cep;
import com.sd.grpc.CepOuterClass.CepResponse;
import com.sd.grpc.CepOuterClass.CreateRequest;
import com.sd.grpc.CepOuterClass.DeleteRequest;
import com.sd.grpc.CepOuterClass.UpdateRequest;
import com.sd.grpc.TransportadoraOuterClass.APITransportadoraResponse;
import com.sd.grpc.TransportadoraOuterClass.Transportadora;
import com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse;
import com.sd.grpc.cepGrpc;
import com.sd.grpc.cepGrpc.cepBlockingStub;
import com.sd.grpc.transportadoraGrpc;
import com.sd.grpc.transportadoraGrpc.transportadoraBlockingStub;

import io.grpc.ManagedChannel;

public class ControllerImpl implements Controller {

	@Override
	public int dealWith(String text, ManagedChannel channel) {

		cepBlockingStub cepStub = cepGrpc.newBlockingStub(channel);
		transportadoraBlockingStub transportadoraStub = transportadoraGrpc.newBlockingStub(channel);

		com.sd.grpc.CepOuterClass.Empty cepEmpty = com.sd.grpc.CepOuterClass.Empty.newBuilder().build();
		com.sd.grpc.TransportadoraOuterClass.Empty transportadoraEmpty = com.sd.grpc.TransportadoraOuterClass.Empty
				.newBuilder().build();

		String[] splittedMessage = text.split(" ");

		switch (splittedMessage[0]) {
		case "create":
			if (splittedMessage[1].equals("cep")) {
				//parse exception
				Cep cep = Cep.newBuilder().setCepInicio(Long.parseLong(splittedMessage[2]))
						.setCepFim(Long.parseLong(splittedMessage[3])).build();
				APICepResponse response = cepStub.create(CreateRequest.newBuilder().setCep(cep).build());
				return response.getResponseCode();
			} else if (splittedMessage[1].equals("transportadora")) {
				Transportadora t = Transportadora.newBuilder().setNome(splittedMessage[2])
						.setIdAbrangencia(Integer.parseInt(splittedMessage[3]))
						.setPeso(Double.parseDouble(splittedMessage[4])).build();
				APITransportadoraResponse response = transportadoraStub.create(
						com.sd.grpc.TransportadoraOuterClass.CreateRequest.newBuilder().setTransportadora(t).build());
				return response.getResponseCode();
			} else // -1 indica que o comando foi digitado incorretamente pelo cliente
				return -1;
		case "update":
			if (splittedMessage[1].equals("cep")) {
				int id = Integer.parseInt(splittedMessage[2]);
				Cep cep = Cep.newBuilder().setCepInicio(Long.parseLong(splittedMessage[3]))
						.setCepFim(Long.parseLong(splittedMessage[4])).build();
				APICepResponse response = cepStub.update(UpdateRequest.newBuilder().setId(id).setCep(cep).build());
				return response.getResponseCode();
			} else if (splittedMessage[1].equals("transportadora")) {
				int id = Integer.parseInt(splittedMessage[2]);
				Transportadora t = Transportadora.newBuilder().setNome(splittedMessage[3])
						.setIdAbrangencia(Integer.parseInt(splittedMessage[4]))
						.setPeso(Double.parseDouble(splittedMessage[5])).build();
				APITransportadoraResponse response = transportadoraStub.create(
						com.sd.grpc.TransportadoraOuterClass.CreateRequest.newBuilder().setTransportadora(t).build());
				return response.getResponseCode();
			} else
				return -1;
		case "delete":
			if (splittedMessage[1].equals("cep")) {
				int id = Integer.parseInt(splittedMessage[2]);
				APICepResponse response = cepStub.delete(DeleteRequest.newBuilder().setId(id).build());
				return response.getResponseCode();
			} else if (splittedMessage[1].equals("transportadora")) {
				int id = Integer.parseInt(splittedMessage[2]);
				APITransportadoraResponse response = transportadoraStub
						.delete(com.sd.grpc.TransportadoraOuterClass.DeleteRequest.newBuilder().setId(id).build());
				return response.getResponseCode();
			} else
				return -1;
		case "readall":
			StringBuilder result = new StringBuilder();
			if (splittedMessage[1].equals("cep")) {
				Iterator<CepResponse> response = cepStub.readall(cepEmpty);

				while (response.hasNext()) {
					CepResponse item = response.next();
					result.append(item.getId()).append(": de ").append(item.getCepInicio()).append(" até ").append(item)
							.append(", ");
				}
				System.out.println(result.toString().substring(0, result.length() - 2));
				return 0;

			} else if (splittedMessage[1].equals("transportadora")) {
				Iterator<TransportadoraResponse> response = transportadoraStub.readall(transportadoraEmpty);

				while (response.hasNext()) {
					TransportadoraResponse item = response.next();

					result.append(item.getId()).append(": ").append(item.getNome()).append(", peso ")
							.append(item.getPeso()).append(" e abrangência de ")
							.append(item.getAbrangencia().getCepInicio()).append(" a ")
							.append(item.getAbrangencia().getCepFim()).append(", ");
				}
				System.out.println(result.toString().substring(0, result.length() - 2));
				return 0;

			} else
				return -1;

		default:
			return -1;
		}

	}

}
