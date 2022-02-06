package HttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class DisplayHandler implements HttpHandler {

    /**
     * HttpHandler requires a handle function that takes a request.
     * TODO: parse html file and output a grid
     * @param exchange the encapsulated http request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Successfully Running Server</h1>";
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }
}