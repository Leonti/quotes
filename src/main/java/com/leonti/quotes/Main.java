package com.leonti.quotes;

import org.eclipse.jetty.server.Server;

public class Main {
	private static final int JETTY_PORT = 8080;

	public static void main(String... args) throws Exception {
		new Main().start();
	}

	public void start() throws Exception {
		Server server = Jetty.init(JETTY_PORT, new GuiceContextListener());
		server.start();
		server.join();
	}
}