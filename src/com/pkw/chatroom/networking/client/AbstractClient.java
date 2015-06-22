package com.pkw.chatroom.networking.client;

import com.pkw.chatroom.networking.AbstractNetworkEntity;
import com.pkw.chatroom.networking.ip.IpAddressFinder;

public abstract class AbstractClient extends AbstractNetworkEntity implements Client {

    protected AbstractClient(IpAddressFinder ipAddressFinder) {
        super(ipAddressFinder);
    }

}
