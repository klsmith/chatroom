package com.pkw.chatroom.networking.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IpifyExternalIpAddressFinder implements ExternalIpAddressFinder {
	private static final String IPIFY_API_URL = "http://api.ipify.org";
	private static final String EXTERNAL_IP_ERROR_MESSAGE = "error finding external ip";

	public static IpifyExternalIpAddressFinder getInstance() {
		return new IpifyExternalIpAddressFinder();
	}

	private IpifyExternalIpAddressFinder() {
	}

	@Override
	public String findExternalIpAddress() {
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
