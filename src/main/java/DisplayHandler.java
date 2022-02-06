import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class DisplayHandler implements HttpHandler {

    /**
     * HttpHandler requires a handle function that takes a request.
     * TODO: parse html file and output a grid
     *
     * @param exchange the encapsulated http request
     * @throws IOException
     */
    public void handle(HttpExchange exchange) throws IOException {
        String html = "";
        try {
            html = Files.readString(Path.of("src/valid.html"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
        exchange.getResponseHeaders().add("Content-Type", "text/html");

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, html.length());
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(html.getBytes());
        responseBody.close();
    }
}