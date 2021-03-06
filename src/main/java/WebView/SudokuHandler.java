package WebView;

import Sudoku.StarterGrids;
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
	private String link(List<List<Integer>> cleanQuery)
	{
		return Tags.createLink("/?" + FormProcessor.nestedListToString(cleanQuery),
		                       "Bookmarkable link",
		                       Tags.Rel.BOOKMARK,
		                       "bookmark");
	}


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
			System.out.println("Request Received:");
			System.out.println(exchange.getRemoteAddress()
			                           .getAddress()
			                           .getHostAddress());
			if(unsanitisedQuery == null) {
				unsanitisedQuery = "";
			}
			//Sanitise string
			try {
				List<List<Integer>> cleanQuery = FormProcessor.validateNumstringToList(unsanitisedQuery);
				response = PageBuilder.build(cleanQuery,
				                             Tags.nest("p class=\"blurb\"",
				                                       "Continue with the grid..." + link(cleanQuery)));
			}
			catch(IllegalArgumentException e) {
				response = PageBuilder.build(StarterGrids.getRandomGrid(),
				                             Tags.nest("p class=\"blurb\"",
				                                       "Complete the grid and verify your attempt.  You can verify as many times as you need to." + link(
						                                       StarterGrids.getRandomGrid())));
			}
			catch(Exception e) {
				System.out.println("Got an unexpected error:\n" + e.getMessage());
				e.printStackTrace();
				response = "Bad Request, Something went wrong.";
			}

		}


		if(exchange.getRequestMethod()
		           .equals("POST")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
			String unsanitisedRequest = reader.lines()
			                                  .filter(line -> line.startsWith("R0-C0"))
			                                  .findFirst()
			                                  .orElse("");

			List<List<Integer>> cleanQuery = FormProcessor.validateFormDataToList(unsanitisedRequest);

			/* The original verifier gets called here... */
			final boolean isValid = SudokuVerifier.verify(cleanQuery);

			response = PageBuilder.build(cleanQuery,
			                             Tags.nest("p class=\"blurb\"",
			                                       String.format("The grid is %s",
			                                                     isValid
			                                                     ? "correct! Well Done"
			                                                     : "not quite right, try again.") + link(cleanQuery)));
		}

		exchange.getResponseHeaders()
		        .add("Content-Type", "text/html");
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
		OutputStream responseBody = exchange.getResponseBody();
		responseBody.write(response.getBytes());
		responseBody.close();
	}
}