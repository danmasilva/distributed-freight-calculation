package com.sd.dfc.client;

import java.util.Scanner;

import com.sd.dfc.config.ReadPropertyFile;
import com.sd.dfc.controller.Controller;
import com.sd.dfc.controller.ControllerImpl;
import com.sd.dfc.principal.Menu;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GRPCClient {
	public static void main(String[] args) {
		ReadPropertyFile prop = new ReadPropertyFile();
		Controller controller = new ControllerImpl();
		
		String ip = prop.getValue("dfc.client.url");
		int port = Integer.parseInt(prop.getValue("dfc.client.port")) ;
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, port)
			.usePlaintext().build();
		
		Menu menu = new Menu();
        System.out.println(menu.presentMenu());

        Scanner s = new Scanner(System.in);
        String text;
        
        while (true) {

            text = s.nextLine();

            if (!(text).equals("sair") && !(text).equals("quit") && !(text).equals("exit")) {
                int response = controller.dealWith(text, channel);
                switch (response) {
                case 200:
                	System.out.println("200 - OK");
                	break;
				case 201:
					System.out.println("201 - Created");
					break;
				case 204:
					System.out.println("204 - No Content");
					break;
				case 400:
					System.out.println("400 - Bad Request");
					break;
				case 404:
					System.out.println("404 - Not Found");
					break;
				case 500:
					System.out.println("500 - Internal Server Error");

				default:
					break;
				}
            } else
                break;
        }
		
		
	}
}
