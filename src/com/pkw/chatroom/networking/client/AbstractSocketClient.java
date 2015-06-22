package com.pkw.chatroom.networking.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.pkw.chatroom.networking.ip.ExternalIpAddressFinder;

public class AbstractSocketClient extends AbstractClient {

    private Socket clientSocket;
    private Thread serverMessageListenerThread;
    private boolean open;

    protected AbstractSocketClient(String ipAddress, int port, ExternalIpAddressFinder ipAddressFinder) throws UnknownHostException,
            IOException {
        super(ipAddressFinder);
        this.clientSocket = new Socket(ipAddress, port);
        open = true;
        serverMessageListenerThread = getServerMessageListenerThread();
        serverMessageListenerThread.start();
    }

    private Thread getServerMessageListenerThread() {
        return new Thread() {

            @Override
            public void run() {
                while (open) {
                    try {
                        Scanner scanner = new Scanner(clientSocket.getInputStream());
                        while (scanner.hasNextLine()) {
                            String message = scanner.nextLine();
                            System.out.println(message);
                        }
                        scanner.close();
                    }
                    catch (IOException e) {
                        // Do Nothing
                    }
                }
            }
        };
    }

    @Override
    public int getPort() {
        return clientSocket.getPort();
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public boolean isConnected() {
        return clientSocket.isConnected();
    }

    @Override
    public void writeMessage(String message) {
        try {
            PrintStream output = new PrintStream(clientSocket.getOutputStream());
            output.write(message.getBytes());
            output.close();
        }
        catch (IOException e) {
            // Do Nothing
        }
    }
}
