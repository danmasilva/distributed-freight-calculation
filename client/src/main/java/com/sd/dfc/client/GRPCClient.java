package com.sd.dfc.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import com.sd.dfc.config.ReadPropertyFile;
import com.sd.dfc.controller.Controller;
import com.sd.dfc.controller.ControllerImpl;
import com.sd.dfc.principal.Menu;
import com.sd.grpc.CepOuterClass.CepResponse;
import com.sd.grpc.cepGrpc.cepStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GRPCClient {
	public static void main(String[] args) {
		ReadPropertyFile prop = new ReadPropertyFile();
		Controller controller = new ControllerImpl();
		
		String ip = prop.getValue("dfc.url");
		int port = Integer.parseInt(prop.getValue("dfc.port")) ;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, port)
			.usePlaintext().build();

		
		Iterator<CepResponse> cepResponse = cepStub.readall(cepEmpty);
		

		Menu menu = new Menu();
        System.out.println(menu.presentMenu());

        Scanner s = new Scanner(System.in);
        String text;
        
        try {

        while (true) {

            text = s.nextLine();

            if (!(text).equals("sair") && !(text).equals("quit") && !(text).equals("exit")) {
                controller.dealWith(text, channel);
            } else
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
		
		
	}
}
