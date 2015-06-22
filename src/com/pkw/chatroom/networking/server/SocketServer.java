package com.pkw.chatroom.networking.server;

import java.io.IOException;

import com.pkw.chatroom.networking.ip.IpifyExternalIpAddressFinder;

public final class SocketServer extends AbstractSocketServer {

    public static SocketServer createOn(int port) throws IOException {
        return new SocketServer(port);
    }

    private SocketServer(int port) throws IOException {
        super(port, IpifyExternalIpAddressFinder.getInstance());
    }

}
