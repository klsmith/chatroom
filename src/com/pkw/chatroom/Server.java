package com.pkw.chatroom;

import java.io.IOException;
import java.net.ServerSocket;

public abstract class Server {

	private ServerSocket serverSocket;
	private boolean isRunning;

	protected Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	protected final void start() {
		isRunning = true;
		final Server thisServer = this;
		new Thread("Server Thread") {
			@Override
			public void run() {
				thisServer.run();
			}
		};
	}

	public boolean isRunning() {
		return isRunning;
	}

	protected abstract void run();
}
