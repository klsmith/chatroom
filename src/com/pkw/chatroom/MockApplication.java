package com.pkw.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public final class MockApplication {

	public static BufferedReader inputReader;

	public static void main(String[] args) {
		boolean loop = true;
		inputReader = new BufferedReader(new InputStreamReader(System.in));
		while (loop) {
			try {
				System.out.print("Hosting or Connecting? ");
				String input = inputReader.readLine().trim();
				if (input.equalsIgnoreCase("hosting")
						|| input.equalsIgnoreCase("host")) {
					System.out.print("Port: ");
					String port = inputReader.readLine().trim();
					String ip = getExternalIP();
					System.out.println("***Server Started on " + ip + ":"
							+ port + "***");
					loop = false;
				} else if (input.equalsIgnoreCase("connecting")
						|| input.equalsIgnoreCase("connect")) {
					System.out.print("IP Address: ");
					String ip = inputReader.readLine().trim();
					System.out.print("Port: ");
					String port = inputReader.readLine().trim();
					System.out.println("*** Connected To " + ip + ":" + port
							+ "***");
					loop = false;
				}
			} catch (IOException e) {
				continue;
			}
		}
		String name = "";
		loop = true;
		while (loop) {
			loop = false;
			try {
				System.out.print("Enter a Username: ");
				name = inputReader.readLine().trim();
			} catch (IOException e) {
				loop = true;
			}
		}
		System.out.println("***Type '/stop' to exit the program***");
		loop = true;
		while (loop) {
			try {
				System.out.print(name + ": ");
				String input = inputReader.readLine();
				if (input.trim().equalsIgnoreCase("/stop")) {
					loop = false;
				}
			} catch (IOException e) {
				continue;
			}
		}
		System.out.println("***Program Stopped***");
	}

	public static String getExternalIP() {
		try {
			URL ipify = new URL("http://api.ipify.org");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ipify.openStream()));
			return in.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error finding external ip";
	}
}
