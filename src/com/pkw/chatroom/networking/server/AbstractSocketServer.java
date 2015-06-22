package com.pkw.chatroom.networking.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pkw.chatroom.networking.IpAddressFinder;

public abstract class AbstractSocketServer extends AbstractServer {

    private ServerSocket serverSocket;
    private List<Socket> clientSocketList;
    private Thread connectionListenerThread;
    private Thread clientInputListenerThread;

    protected AbstractSocketServer(int port, IpAddressFinder ipAddressFinder) throws IOException {
        super(ipAddressFinder);
        serverSocket = new ServerSocket(port);
        clientSocketList = new ArrayList<Socket>();
        connectionListenerThread = getConnectionListenerThread();
        clientInputListenerThread = getClientInputListenerThread();
    }

    private Thread getConnectionListenerThread() {
        return new Thread("Connection Listener") {

            @Override
            public void run() {
                while (isRunning()) {
                    try {
                        clientSocketList.add(serverSocket.accept());
                    }
                    catch (IOException e) {
                        continue;
                    }
                }
            }
        };
    }

    private Thread getClientInputListenerThread() {
        return new Thread("Client Input Listener") {

            @Override
            public void run() {
                while (isRunning()) {
                    for (Socket clientSocket : clientSocketList) {
                        try {
                            Scanner scanner = new Scanner(clientSocket.getInputStream());
                            while (scanner.hasNext()) {
                                String message = scanner.next();
                                writeMessageIgnoreSocket(message, clientSocket);
                            }
                            scanner.close();
                        }
                        catch (IOException e) {
                            continue;
                        }
                    }
                }
            }
        };
    }

    @Override
    public void writeMessage(String message) {
        writeMessageIgnoreSocket(message, null);
    }

    private void writeMessageIgnoreSocket(String message, Socket toIgnore) {
        for (Socket clientSocket : clientSocketList) {
            try {
                if (clientSocket.equals(toIgnore)) {
                    continue;
                }
                else {
                    PrintStream output = new PrintStream(clientSocket.getOutputStream());
                    output.write(message.getBytes());
                    output.close();
                }
            }
            catch (IOException e) {
                continue;
            }
        }
    }

    @Override
    public void start() {
        super.start();
        connectionListenerThread.start();
        clientInputListenerThread.start();
    }

    @Override
    public void stop() {
        super.stop();
        try {
            serverSocket.close();
        }
        catch (IOException e) {
        }
        for (Socket clientSocket : clientSocketList) {
            try {
                clientSocket.close();
            }
            catch (IOException e) {
                continue;
            }
        }
    }
}
