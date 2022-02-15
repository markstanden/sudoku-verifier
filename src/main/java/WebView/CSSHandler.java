package WebView;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class CSSHandler implements HttpHandler
{

	/**
	 * HttpHandler requires a handle function that takes a request.
	 *
	 * @param exchange
	 * 		the encapsulated http request
	 *
	 * @throws IOException
	 */
	public void handle(HttpExchange exchange) throws IOException
	{
		exchange.getResponseHeaders()
		        .add("Content-Type", "text/css");

		File file = new File("css/styles.css");
		byte[] bytearray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bis.read(bytearray, 0, bytearray.length);

		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, file.length());
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(bytearray);
		responseBody.close();
	}
}