package com.sd.dfc.service;

import com.sd.dfc.controller.DataControllerTransportadoraImpl;
import com.sd.dfc.model.Transportadora;
import com.sd.grpc.Business;
import com.sd.grpc.Business.PrecoRequest;
import com.sd.grpc.Business.PrecoReturn;
import com.sd.grpc.pricingGrpc.pricingImplBase;
import io.grpc.stub.StreamObserver;
import java.util.List;

public class PricingService extends pricingImplBase {
    public final double TAXA_SERVICO = 1.2;

    DataControllerTransportadoraImpl dataControllerTransportadora = new DataControllerTransportadoraImpl();
    @Override
    public void defPricing(PrecoRequest request, StreamObserver<PrecoReturn> responseObserver) {

        System.out.println("list price for a cep");

        int cep = (int) request.getCep();
        double peso = request.getPeso();

        if (cep == 0 || peso == 0.0 ) {
            Business.PrecoReturn.Builder response = Business.PrecoReturn.newBuilder();
            response.setResponseCode(400).setResponsemessage("Bad Request");
            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
            return;
        }
        String query = "readall transportadora";
        List<Transportadora> transportadoras = (List<Transportadora>) dataControllerTransportadora.readAll(query.split(" "));
        for (Transportadora t : transportadoras) {
            if(t.getAbrangencia().getCepInicio() < cep && t.getAbrangencia().getCepFim()>cep) {
                Business.PrecoReturn.Builder response = Business.PrecoReturn.newBuilder();
                response.setResponseCode((int) 200).setResponsemessage("OK");
                response.setTransportadora(t.getNome());
                response.setPrice((double) t.getPeso() * TAXA_SERVICO);

                responseObserver.onNext(response.build());
            }
            
        }
        responseObserver.onCompleted();
    }
}
