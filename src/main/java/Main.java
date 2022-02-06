import HttpServer.Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server Starting...");
        Server server = Server.start();
        System.out.println("server started on port: " + server.getPort());
    }
}
