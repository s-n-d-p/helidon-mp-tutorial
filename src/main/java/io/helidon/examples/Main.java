package io.helidon.examples;

import io.helidon.microprofile.server.Server;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {
        final Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/greet");
    }

    static Server startServer() {
        return Server.create().start();
    }
}