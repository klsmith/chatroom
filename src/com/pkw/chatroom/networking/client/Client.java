package com.pkw.chatroom.networking.client;

import com.pkw.chatroom.networking.NetworkEntity;

public interface Client extends NetworkEntity {

    public void writeMessage(String message);

    public void close();

    public boolean isConnected();
}
