import HttpServer.Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server Starting...");
        System.out.println(Server.start() ? "Running" : "Failed to Start");
    }
}
