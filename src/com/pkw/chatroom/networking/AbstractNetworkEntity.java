package com.pkw.chatroom.networking;

import com.pkw.chatroom.networking.ip.IpAddressFinder;

public abstract class AbstractNetworkEntity implements NetworkEntity {

    private IpAddressFinder ipAddressFinder;

    protected AbstractNetworkEntity(IpAddressFinder ipAddressFinder) {
        this.ipAddressFinder = ipAddressFinder;
    }

    @Override
    public String ipAddress() {
        return ipAddressFinder.findIpAddress();
    }

}
