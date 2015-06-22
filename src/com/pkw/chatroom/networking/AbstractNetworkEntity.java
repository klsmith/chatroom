package com.pkw.chatroom.networking;

import com.pkw.chatroom.networking.ip.ExternalIpAddressFinder;

public abstract class AbstractNetworkEntity implements NetworkEntity {

    private ExternalIpAddressFinder ipAddressFinder;

    protected AbstractNetworkEntity(ExternalIpAddressFinder ipAddressFinder) {
        this.ipAddressFinder = ipAddressFinder;
    }

    @Override
    public String ipAddress() {
        return ipAddressFinder.findExternalIpAddress();
    }

}
