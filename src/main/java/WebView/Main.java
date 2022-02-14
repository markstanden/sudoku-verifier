package WebView;

public class Main {
    final static int DEFAULT_PORT = 5000;
    public static void main(String[] args) {
        System.out.println("WebView.Server Starting...");
        Server server = Server.start(DEFAULT_PORT);
        assert server != null;
        System.out.println("WebView.Server started on port: " + server.getPort());
    }
}