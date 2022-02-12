package WebView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GridBuilder
{
	public static String addGrid(final List<List<Integer>> grid)
	{
		final String gridAsHTML = IntStream.range(0, grid.size())
										   .mapToObj(rowNumber -> addGridRow(rowNumber, grid.get(rowNumber)))
										   .collect(Collectors.joining("\n"));

		final String verifyButton = HtmlGenerator.wrap("button", "Verify");
		final String gridAsHTMLWithButtons = gridAsHTML.concat("\n")
													   .concat(verifyButton);

		return HtmlGenerator.nest("form id=\"grid-form\" method=\"post\" action=\"\"",

								  gridAsHTMLWithButtons,

								  "form");
	}


	private static String addGridRow(final int rowNumber, final List<Integer> row)
	{
		final String rowsAsHTML = IntStream.range(0, row.size())
										   .mapToObj(colNumber -> addGridCell(rowNumber, colNumber, row.get(colNumber)))
										   .collect(Collectors.joining("\n"));

		return HtmlGenerator.nest(String.format("fieldset class=\"grid-row\" name=\"Row%d\" form=\"grid-form\"",
												rowNumber), rowsAsHTML, "fieldset");
	}


	private static String addGridCell(final int rowNumber, final int colNumber, final int value)
	{
		return HtmlGenerator.wrap("span class=\"grid-cell\"",
								  String.format(
										  "<input name=\"R%d-C%d\" type=\"number\" min=\"0\" max=\"9\" value=\"%s\"/>",
										  rowNumber,
										  colNumber,
										  value == 0 ? "" : value),
								  "span");
	}
}