package com.pkw.chatroom.networking.server;

import com.pkw.chatroom.networking.NetworkEntity;

public interface Server extends NetworkEntity {

    public void start();

    public void stop();

    public boolean isRunning();

    public void writeMessage(String message);

}
