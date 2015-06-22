package com.pkw.chatroom.networking.server;

import com.pkw.chatroom.networking.IpAddressFinder;

public abstract class AbstractServer implements Server {

    private IpAddressFinder ipAddressFinder;
    private boolean running;

    protected AbstractServer(IpAddressFinder ipAddressFinder) {
        this.ipAddressFinder = ipAddressFinder;
        running = false;
    }

    @Override
    public final String getIpAddress() {
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
    public final boolean isRunning() {
        return running;
    }

}
