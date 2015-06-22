package com.pkw.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pkw.chatroom.networking.client.Client;
import com.pkw.chatroom.networking.client.SocketClient;

public class ClientApplication {

    public static void main(String[] args) {
        System.out.println("***Program Start***");
        Client client = getClient();
        String username = getUsername();
        System.out.println("***Type '/stop' to stop the program***");
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        while (client.isConnected()) {
            try {
                String input = inputReader.readLine();
                if (input.equalsIgnoreCase("/stop")) {
                    client.close();
                    System.out.println("***Connection closed***");
                }
                else {
                    client.writeMessage(username + ": " + input);
                }
            }
            catch (IOException e) {
                continue;
            }
        }
        System.out.println("***Program End***");
    }

    private static Client getClient() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        Client client = null;
        boolean looping = true;
        while (looping) {
            looping = false;
            System.out.print("IP Address: ");
            String ipAddress = "";
            try {
                ipAddress = inputReader.readLine().trim();
            }
            catch (IOException e) {
                System.out.println("Error reading input! Please try again.");
                looping = true;
                continue;
            }
            System.out.print("Port: ");
            int port = 0;
            try {
                port = Integer.parseInt(inputReader.readLine().trim());
            }
            catch (IOException e) {
                System.out.println("Error reading input! Please try again.");
                looping = true;
                continue;
            }
            catch (NumberFormatException e) {
                System.out.println("Port was not a number! Please try again.");
                looping = true;
                continue;
            }
            try {
                client = SocketClient.createFor(ipAddress, port);
            }
            catch (IOException e) {
                System.out.println("Error connecting to ip address " + ipAddress + " and port " + port + " please try again.");
                looping = true;
            }
            if (client == null) {
                System.out.println("Error connecting to ip address " + ipAddress + " and port " + port + " please try again.");
                looping = true;
            }
        }
        if (client != null) {
            System.out.println("*** Connected To " + client.getIpAddress() + ":" + client.getPort() + "***");
        }
        try {
            inputReader.close();
        }
        catch (IOException e) {
        }
        return client;
    }

    private static String getUsername() {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String username = "";
        boolean loop = true;
        while (loop) {
            loop = false;
            try {
                System.out.print("Enter a Username: ");
                username = inputReader.readLine().trim();
            }
            catch (IOException e) {
                System.out.println("Error reading input. Please try again.");
            }
        }
        try {
            inputReader.close();
        }
        catch (IOException e) {
        }
        return username;
    }
}
