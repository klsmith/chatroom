package com.pkw.chatroom.networking.client;

import com.pkw.chatroom.networking.NetworkEntity;

public interface Client extends NetworkEntity {

    public void connectTo(String ipAddress, int port);

}
