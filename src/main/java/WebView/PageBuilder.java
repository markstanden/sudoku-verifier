package WebView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PageBuilder
{
	public static String build(List<List<Integer>> sanitisedQuery, String messageHTML) throws IOException
	{

		try {
			String header = Files.readString(Path.of("src/assets/html/header.html"));
			String css = Files.readString(Path.of("src/assets/html/styles.css"));
			String title = Files.readString(Path.of("src/assets/html/title.html"));
			String footer = Files.readString(Path.of("src/assets/html/footer.html"));

			String style = HtmlGenerator.nest("style", css);
			String body = HtmlGenerator.nest("body",
											 title.concat("\n")
												  .concat(messageHTML)
												  .concat("\n")
												  .concat(GridBuilder.addGrid(sanitisedQuery)));
			return HtmlGenerator.buildHtml(header, style, body, footer);
		}
		catch(FileNotFoundException e) {
			System.out.println("An error occurred attempting to read the html file.");
			throw e;
		}
	}
}