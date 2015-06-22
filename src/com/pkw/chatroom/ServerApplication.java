package com.pkw.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pkw.chatroom.networking.server.Server;
import com.pkw.chatroom.networking.server.SocketServer;

public class ServerApplication {

    public static void main(String[] args) {
        System.out.println("***Program Start***");
        Server server = getServer();
        server.start();
        System.out.println("***Server Started on " + server.getIpAddress() + ":" + server.getPort() + "***");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("***Type '/stop' to stop the server***");
        while (server.isRunning()) {
            try {
                String input = inputReader.readLine();
                if (input.equalsIgnoreCase("/stop")) {
                    server.stop();
                    System.out.println("***Server Stopped***");
                }
            }
            catch (IOException e) {
                continue;
            }
        }
        System.out.println("***Program End***");
    }

    private static Server getServer() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        Server server = null;
        boolean looping = true;
        while (looping) {
            looping = false;
            System.out.print("Port: ");
            int port = 0;
            try {
                port = Integer.parseInt(inputReader.readLine().trim());
                server = SocketServer.createOn(port);
            }
            catch (NumberFormatException | IOException e) {
                System.out.println("Could not connect to port " + port + " please try again.");
                looping = true;
            }
        }
        try {
            inputReader.close();
        }
        catch (IOException e) {
        }
        return server;
    }
}
