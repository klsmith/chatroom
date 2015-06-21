package com.pkw.chatroom.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IpifyIpFinder implements IpFinder {
	private static final String IPIFY_API_URL = "http://api.ipify.org";
	private static final String EXTERNAL_IP_ERROR_MESSAGE = "error finding external ip";

	public static IpifyIpFinder getInstance() {
		return new IpifyIpFinder();
	}

	private IpifyIpFinder() {
	}

	@Override
	public String getIp() {
		try {
			URL ipify = new URL(IPIFY_API_URL);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ipify.openStream()));
			return in.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EXTERNAL_IP_ERROR_MESSAGE;
	}

}