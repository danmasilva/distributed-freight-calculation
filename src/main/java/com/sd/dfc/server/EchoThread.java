package com.sd.dfc.server;

import com.sd.dfc.client.SocketClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class EchoThread extends Thread {

    private Socket socket;

    EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] splittedMessage = inputLine.split(" ");
                    List<String> splittedList = new ArrayList<>(Arrays.asList(splittedMessage));
                    splittedList.remove(0);
                    byte[] response;
                    switch (splittedMessage[0]) {
                        case SocketClient.INSERT:
                        case SocketClient.CREATE:
                        case SocketClient.INSERIR:
                            ServerThread.database.create(String.join(" ", splittedList).getBytes());
                            out.println("Message inserted: " + String.join(" ", splittedList));
                            break;
                        case SocketClient.CHANGE:
                        case SocketClient.ALTERAR:
                        case SocketClient.UPDATE:
                            response = ServerThread.database.update(BigInteger.valueOf(Long.parseLong(splittedList.remove(0))),
                                    String.join(" ", splittedList).getBytes());
                            if (response != null) {
                                out.println("Previous message: " + new String(response) + ", New message: "
                                        + String.join(" ", splittedList));
                            } else {
                                out.println("Fail on update message.");
                            }
                            break;
                        case SocketClient.DELETAR:
                        case SocketClient.DELETE:
                            response = ServerThread.database.delete(BigInteger.valueOf(Long.parseLong(splittedList.get(0))));
                            if (response != null) {
                                out.println("Message removed: " + new String(response));
                            } else {
                                out.println("Fail on removing item with id " + splittedList.get(0));
                            }
                            break;
                        case SocketClient.READ_ALL:
                        case SocketClient.LER_TODOS:
                            Map<BigInteger, byte[]> map = ServerThread.database.readAll();
                            StringBuilder result = new StringBuilder();
                            for (Entry<BigInteger, byte[]> entry : map.entrySet()) {
                                result.append(String.valueOf(entry.getKey())).append(": ").append(new String(entry.getValue())).append(", ");
                            }
                            // remove the last comma
                            if (result.length() != 0) {
                                out.println(result.toString().substring(0, result.length() - 2));
                            } else // para nao dar erro de posiçao
                            {
                                out.println("Database vazio");
                            }

                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
