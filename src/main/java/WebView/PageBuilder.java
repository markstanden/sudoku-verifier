package WebView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PageBuilder
{
	public static String build(List<List<Integer>> sanitisedQuery, String messageHTML)
			throws IOException, IllegalArgumentException
	{
		if(sanitisedQuery == null) {
			throw new IllegalArgumentException("Supplied a null grid to PageBuilder");
		}
		if(FormProcessor.totalValues(sanitisedQuery) != 81) {
			throw new IllegalArgumentException("Invalid Query String");
		}
		try {
			String head = Files.readString(Path.of("src/main/resources/html/head.html"));
			String css = Files.readString(Path.of("src/main/resources/html/styles.css"));
			String title = Files.readString(Path.of("src/main/resources/html/title.html"));
			String footer = Files.readString(Path.of("src/main/resources/html/footer.html"));

			String style = Tags.nest("head", Tags.nest("style", css));
			String body = Tags.nest("body",
			                        title.concat("\n")
			                             .concat(messageHTML)
			                             .concat("\n")
			                             .concat(GridBuilder.addGrid(sanitisedQuery))
			                             .concat("\n")
			                             .concat(footer));
			return Tags.buildHtml(head, style, body);
		}
		catch(FileNotFoundException e) {
			System.out.println("An error occurred attempting to read the html file.");
			throw e;
		}
		catch(IllegalArgumentException e) {
			System.out.println("Cannot build grid, Invalid Query String");
			throw e;
		}
	}

}