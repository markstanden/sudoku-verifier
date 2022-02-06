package HttpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class VerifyHandler implements HttpHandler {

    /**
     * HttpHandler requires a handle function that takes a request.
     * TODO: parse POST request
     *
     * @param exchange the encapsulated http request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            reader.lines()
                    .forEach(System.out::println);
        }
        String response = "<h1>Successfully reached page</h1>";
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(response.getBytes());
        responseBody.close();
    }
}