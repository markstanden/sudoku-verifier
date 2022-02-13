package WebView;

import Sudoku.SudokuVerifier;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;

public class SudokuHandler implements HttpHandler
{
	final List<List<Integer>> BASE_GRID = FormProcessor.validateNumstringToList(
			"085601023462308150003005068578030649620984735349567812050803206836000504290756380");

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
		if(exchange.getRequestMethod()
				   .equals("GET")) {
			String unsanitisedQuery = exchange.getRequestURI()
											  .getQuery();
			//Sanitise string
			try {
				List<List<Integer>> cleanQuery = FormProcessor.validateFormDataToList(unsanitisedQuery);
				response = PageBuilder.build(cleanQuery,
											 "<p class=\"blurb\">Continue with the grid...</p>");
			}
			catch(IllegalArgumentException e) {
				response = PageBuilder.build(BASE_GRID,
											 "<p class=\"blurb\">Complete the grid and verify your attempt." +
													 "  You can verify as many times as you need to." +
													 "</p>");
			}

		}


		if(exchange.getRequestMethod()
				   .equals("POST")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
			String unsanitisedRequest = reader.lines()
											  .filter(line -> line.startsWith("R0-C0"))
											  .findFirst()
											  .orElse("");

			List<List<Integer>> sanitisedGrid = FormProcessor.validateFormDataToList(unsanitisedRequest);
			boolean isValid = SudokuVerifier.verify(sanitisedGrid);
			response = PageBuilder.build(sanitisedGrid,
										 HtmlGenerator.nest("p class=\"blurb\"",
															String.format("The grid is %s",
																		  isValid
																		  ? "correct! Well " + "Done"
																		  : "not quite right, try again."),
															"p"));
		}

		exchange.getResponseHeaders()
				.add("Content-Type", "text/html");
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(response.getBytes());
		responseBody.close();
	}


}