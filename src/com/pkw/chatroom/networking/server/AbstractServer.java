package com.pkw.chatroom.networking.server;

import com.pkw.chatroom.networking.AbstractNetworkEntity;
import com.pkw.chatroom.networking.ip.ExternalIpAddressFinder;

public abstract class AbstractServer extends AbstractNetworkEntity implements Server {

    private boolean running;

    protected AbstractServer(ExternalIpAddressFinder ipAddressFinder) {
        super(ipAddressFinder);
        running = false;
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
