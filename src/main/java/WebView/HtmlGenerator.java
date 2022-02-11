package WebView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HtmlGenerator
{

	public static final String DOCTYPE = "<!DOCTYPE html>";
	public static final String HTML_OPENING_TAG = "<html lang=\"en\">";
	public static final String HTML_CLOSING_TAG = "</html>";


	public static String createGrid(final List<List<Integer>> grid)
	{
		final String gridAsHTML = IntStream.range(0, grid.size())
				.mapToObj(rowNumber -> createGridRow(rowNumber, grid.get(rowNumber)))
				.collect(Collectors.joining(""));

		final String addVerifyButton = inlineWrap("button class=\"verify\"", "Verify", "button");
		final String addResetButton = inlineWrap("button class=\"reset\"", "Reset", "button");

		final String wrappedGridAsHTML = wrap("div class=\"grid-full\"",
											  gridAsHTML,
											  "div"
											 );
		final String gridAsHTMLWithButtons = wrappedGridAsHTML.concat(addVerifyButton).concat("\n").concat(addResetButton);

		return wrap("form id=\"grid-form\" method=\"post\" action=\"\"",

					gridAsHTMLWithButtons,

					"form"
				   );
	}


	private static String createGridRow(final int rowNumber, final List<Integer> row)
	{
		final String rowsAsHTML = IntStream.range(0, row.size())
				.mapToObj(colNumber -> createGridCell(rowNumber, colNumber, row.get(colNumber)))
				.collect(Collectors.joining("\n"));

		return wrap(String.format("fieldset class=\"grid-row\" name=\"Row%d\" form=\"grid-form\"", rowNumber),
					rowsAsHTML,
					"fieldset");
	}


	private static String createGridCell(final int rowNumber, final int colNumber, final int value)
	{
		return inlineWrap("span class=\"grid-cell\"",
						  String.format(
								  "<input name=\"R%d-C%d\" type=\"number\" min=\"0\" max=\"9\" value=\"%d\"/>",
								  rowNumber,
								  colNumber,
								  value),
						  "span");
	}


	public static String wrap(final String openingTag, final String html)
	{
		return HtmlGenerator.wrap(openingTag, html, openingTag);
	}


	public static String wrap(final String openingTag, final String html, final String closingTag)
	{
		return String.format("<%s>%n%s</%s>%n", openingTag, html.indent(4), closingTag);
	}


	public static String inlineWrap(final String openingTag, final String html, final String closingTag)
	{
		return String.format("<%s>%s</%s>", openingTag, html, closingTag);
	}


	public static String buildHtml(final String... components)
	{
		String[] result = new String[]{DOCTYPE, HTML_OPENING_TAG, String.join("\n", components), HTML_CLOSING_TAG};
		return String.join("\n", result);
	}
}