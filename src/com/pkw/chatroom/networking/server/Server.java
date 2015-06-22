package com.pkw.chatroom.networking.server;

public interface Server {

    public void start();

    public void stop();

    public boolean isRunning();

    public String getIpAddress();

}
