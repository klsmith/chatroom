package com.pkw.chatroom.networking.server;

import com.pkw.chatroom.networking.IpAddressFinder;

public abstract class AbstractServer implements Server {

    private IpAddressFinder ipAddressFinder;
    private boolean running;

    protected AbstractServer(int port, IpAddressFinder ipAddressFinder) {
        this.ipAddressFinder = ipAddressFinder;
        running = false;
    }

    @Override
    public String getIpAddress() {
        return ipAddressFinder.getIpAddress();
    }

    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

}
