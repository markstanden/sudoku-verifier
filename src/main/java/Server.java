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

    public static Server start(final int port) {
        Server sudoku;
        try {
            sudoku = new Server(port);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        sudoku.server.createContext("/", new SudokuHandler());
        sudoku.server.start();
        return sudoku;
    }


    public int getPort() {
        return port;
    }
}