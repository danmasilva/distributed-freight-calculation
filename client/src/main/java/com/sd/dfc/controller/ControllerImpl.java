package com.sd.dfc.controller;

import java.math.BigInteger;
import java.util.Iterator;

import com.sd.grpc.Business;
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
import com.sd.grpc.pricingGrpc.pricingBlockingStub;
import com.sd.grpc.pricingGrpc;
import com.sd.grpc.Business.PrecoRequest;
import com.sd.grpc.Business.PrecoReturn;


import io.grpc.ManagedChannel;

public class ControllerImpl implements Controller {

	@Override
	public int dealWith(String text, ManagedChannel channel) {

		cepBlockingStub cepStub = cepGrpc.newBlockingStub(channel);
		transportadoraBlockingStub transportadoraStub = transportadoraGrpc.newBlockingStub(channel);
		pricingBlockingStub pricingStub = pricingGrpc.newBlockingStub(channel);

		com.sd.grpc.CepOuterClass.Empty cepEmpty = com.sd.grpc.CepOuterClass.Empty.newBuilder().build();
		com.sd.grpc.TransportadoraOuterClass.Empty transportadoraEmpty = com.sd.grpc.TransportadoraOuterClass.Empty
				.newBuilder().build();



		String[] splittedMessage = text.split(" ");

		switch (splittedMessage[0]) {
		case "create":
			try {
				if (splittedMessage[1].equals("cep")) {
					Cep cep = Cep.newBuilder().setCepInicio(Long.parseLong(splittedMessage[2]))
							.setCepFim(Long.parseLong(splittedMessage[3])).build();
					APICepResponse response = cepStub.create(CreateRequest.newBuilder().setCep(cep).build());
					return response.getResponseCode();
				} else if (splittedMessage[1].equals("transportadora")) {
					Transportadora t = Transportadora.newBuilder().setNome(splittedMessage[2])
							.setIdAbrangencia(Integer.parseInt(splittedMessage[3]))
							.setPeso(Double.parseDouble(splittedMessage[4])).build();
					APITransportadoraResponse response = transportadoraStub
							.create(com.sd.grpc.TransportadoraOuterClass.CreateRequest.newBuilder().setTransportadora(t)
									.build());
					return response.getResponseCode();
				} else
					return 400;
			} catch (Exception e) {
				return 400;
			}
		case "update":
			try {
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
					APITransportadoraResponse response = transportadoraStub
							.update(com.sd.grpc.TransportadoraOuterClass.UpdateRequest.newBuilder().setId(id)
									.setTransportadora(t).build());
					return response.getResponseCode();
				} else
					return 400;
			} catch (Exception e) {
				return 400;
			}
		case "delete":
			try {
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
					return 400;
			} catch (Exception e) {
				return 400;
			}
		case "readall":
			try {
				StringBuilder result = new StringBuilder();
				if (splittedMessage[1].equals("cep")) {
					Iterator<CepResponse> response = cepStub.readall(cepEmpty);

					while (response.hasNext()) {
						CepResponse item = response.next();
						result.setLength(0);
						result.append(item.getId()).append(": de ").append(item.getCepInicio()).append(" até ")
								.append(item.getCepFim());
						System.out.println(result);
					}
					return 200;

				} else if (splittedMessage[1].equals("transportadora")) {
					Iterator<TransportadoraResponse> response = transportadoraStub.readall(transportadoraEmpty);

					while (response.hasNext()) {
						TransportadoraResponse item = response.next();
						result.setLength(0);
						result.append(item.getId()).append(": ").append(item.getNome()).append(", peso ")
								.append(item.getPeso()).append(" e abrangência de ")
								.append(item.getAbrangencia().getCepInicio()).append(" a ")
								.append(item.getAbrangencia().getCepFim());
						System.out.println(result);
					}
					return 200;

				} else
					return 400;
			} catch (Exception e) {
				return 400;
			}
		case "price":
			try {
				StringBuilder result = new StringBuilder();
				Long cep = Long.parseLong(splittedMessage[1]);
				Double peso = Double.parseDouble(splittedMessage[2]);
				Iterator<PrecoReturn> response = pricingStub.defPricing(PrecoRequest.newBuilder().setCep(cep).setPeso(peso).build());

				while (response.hasNext()) {
					PrecoReturn item = response.next();
					result.setLength(0);
					result.append("Transportadora "+ item.getTransportadora() +"\n"+
							      "Valor: "+ item.getPrice() + "\n"+
							      "Mensagem: "+ item.getResponsemessage() +"\n"+
								  "HTTP-CODE "+ item.getResponseCode() +"\n");
					System.out.println(result);
				}
				return 200;


			} catch (Exception e) {
				return 400;
			}

		default:
			return 400;
		}
	}

}
