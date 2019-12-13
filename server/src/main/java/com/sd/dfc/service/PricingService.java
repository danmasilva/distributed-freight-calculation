package com.sd.dfc.service;

import com.google.common.math.BigIntegerMath;
import com.sd.dfc.controller.DataControllerTransportadoraImpl;
import com.sd.dfc.model.Transportadora;
import com.sd.grpc.Business;
import com.sd.grpc.Business.PrecoRequest;
import com.sd.grpc.Business.PrecoReturn;
import com.sd.grpc.pricingGrpc.pricingImplBase;
import io.grpc.stub.StreamObserver;
import org.testng.Assert;

import java.math.BigInteger;
import java.util.List;

public class PricingService extends pricingImplBase {
    private final double TAXA_SERVICO = 1.2;

    DataControllerTransportadoraImpl dataControllerTransportadora = new DataControllerTransportadoraImpl();
    @Override
    public void defPricing(PrecoRequest request, StreamObserver<PrecoReturn> responseObserver) {

        System.out.println("list price for a cep");

        int cep = (int) request.getCep();
        double peso = request.getPeso();


        if (cep == 0 || peso == 0.0  || cep < 0 || peso < 0.0 ) {
            Business.PrecoReturn.Builder response = Business.PrecoReturn.newBuilder();
            returnResponse(responseObserver, response, 400, "Bad Request");
            return;
        }
        String query = "readall transportadora";
        Business.PrecoReturn.Builder response = Business.PrecoReturn.newBuilder();
        List<Transportadora> transportadoras = (List<Transportadora>) dataControllerTransportadora.readAll(query.split(" "));
        isNullOrEmptyTransportadora(responseObserver, response, transportadoras);

        boolean emptyResponse=true;
        for (Transportadora t : transportadoras) {
            if(t.getAbrangencia().getCepInicio() <= cep && t.getAbrangencia().getCepFim() >= cep) {
                response.setTransportadora(t.getNome());
                response.setPrice((double) t.getPeso() * TAXA_SERVICO);
                responseObserver.onNext(response.build());
                emptyResponse=false;
            }
        }
        if(emptyResponse) {
            returnResponse(responseObserver, response, 200, "OK. Não ha transportadora para esse CEP");
        } else {
            returnResponse(responseObserver, response, 200, "OK.");
        }

    }

    private void isNullOrEmptyTransportadora(StreamObserver<PrecoReturn> responseObserver, PrecoReturn.Builder response, List<Transportadora> transportadoras) {
        if(transportadoras.isEmpty()) {
            returnResponse(responseObserver, response, 200, "OK. Não ha transportadora na base.");
            return;
        }
        if(transportadoras == null){
            returnResponse(responseObserver, response, 500, "Internal Server Error.");
            return;
        }
    }

    private void returnResponse(StreamObserver<PrecoReturn> responseObserver, PrecoReturn.Builder response, int i, String s) {
        response.setResponseCode(i).setResponsemessage(s);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
