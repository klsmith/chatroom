package com.pkw.chatroom.networking.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.pkw.chatroom.networking.IpFinder;

public abstract class Server {

	private ServerSocket serverSocket;
	private IpFinder ipFinder;
	private boolean isRunning;

	protected Server(int port, IpFinder ipFinder) throws IOException {
		serverSocket = new ServerSocket(port);
		this.ipFinder = ipFinder;
		isRunning = false;
	}

	protected final String getIp() {
		return ipFinder.getIp();
	}

	protected final void start() {
		isRunning = true;
		final Server thisServer = this;
		new Thread("Server Thread") {
			@Override
			public void run() {
				thisServer.run();
			}
		}.start();
	}

	public boolean isRunning() {
		return isRunning;
	}

	protected abstract void run();
}
