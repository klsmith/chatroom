package com.pkw.chatroom.networking.client;

import com.pkw.chatroom.networking.AbstractNetworkEntity;
import com.pkw.chatroom.networking.ip.ExternalIpAddressFinder;

public abstract class AbstractClient extends AbstractNetworkEntity implements Client {

    protected AbstractClient(ExternalIpAddressFinder ipAddressFinder) {
        super(ipAddressFinder);
    }

}
