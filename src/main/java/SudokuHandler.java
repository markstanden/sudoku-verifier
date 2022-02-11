import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuHandler implements HttpHandler
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
		String response = "";
		if(exchange.getRequestMethod().equals("GET")) {
			try {
				String header = Files.readString(Path.of("src/assets/html/header.html"));
				String css = Files.readString(Path.of("src/assets/html/styles.css"));
				String title = Files.readString(Path.of("src/assets/html/title.html"));
				String grid = Files.readString(Path.of("src/assets/html/grid.html"));
				String footer = Files.readString(Path.of("src/assets/html/footer.html"));

				String style = wrap(css, "style");
				String body = wrap(title.concat(grid), "body");
				response = ("<!DOCTYPE html>\n" +
						"<html lang=\"en\">\n")
						.concat(header)
						.concat(style)
						.concat(body)
						.concat(footer)
						.concat("<\\html>");
			}
			catch(FileNotFoundException e) {
				System.out.println("An error occurred attempting to read the html file.");
				throw e;
			}
		}

		String formData = "";
		if(exchange.getRequestMethod().equals("POST")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
			formData = reader.lines()
					.filter(line -> line.startsWith("R0-C0"))
					.findFirst()
					.orElse("");

			boolean isValid = SudokuVerifier.verify(SudokuGrid.fromFormData(formData));
			response = "<h1>Grid is " + (isValid ? "Valid" : "Invalid") + "</h1>";
		}

		exchange.getResponseHeaders().add("Content-Type", "text/html");
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(response.getBytes());
		responseBody.close();
	}


	private String wrap(String html, String tag)
	{
		return String.format("%n%n<%s>%n%s%n</%s>%n", tag, html.indent(4), tag);
	}
}