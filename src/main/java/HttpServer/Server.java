package HttpServer;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    private final int port;
    private final HttpServer server;

    /**
     * Constructor to use custom port
     *
     * @param port the port to listen on
     */
    private Server(int port) throws java.io.IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        this.port = port;
    }

    public static boolean start() {
        Server server;
        try {
            server = new Server(5000);
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        System.out.println("server started at " + server.port);
        server.server.createContext("/", new DisplayHandler());
        server.server.createContext("/verify/", new VerifyHandler());
        server.server.start();
        return true;
    }
}
