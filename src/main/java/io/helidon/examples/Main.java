package io.helidon.examples;

import java.io.IOException;
import java.util.logging.LogManager;

import io.helidon.microprofile.server.Server;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        setUpLogging();
        final Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/greet");
    }

    private static void setUpLogging() throws IOException {
        LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
    }

    static Server startServer() {
        return Server.create().start();
    }
}