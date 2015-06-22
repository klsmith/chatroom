package com.pkw.chatroom.networking.client;

import java.io.IOException;
import java.net.UnknownHostException;

import com.pkw.chatroom.networking.ip.IpifyExternalIpAddressFinder;

public final class SocketClient extends AbstractSocketClient {

    public static Client createFor(String ipAddress, int port) throws UnknownHostException, IOException {
        return new SocketClient(ipAddress, port);
    }

    private SocketClient(String ipAddress, int port) throws UnknownHostException, IOException {
        super(ipAddress, port, IpifyExternalIpAddressFinder.getInstance());
    }

}
