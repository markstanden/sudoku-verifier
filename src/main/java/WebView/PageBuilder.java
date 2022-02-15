package WebView;

import java.util.List;

public class PageBuilder
{
	public static String build(List<List<Integer>> sanitisedQuery, String messageHTML) throws IllegalArgumentException
	{
		if(sanitisedQuery == null) {
			throw new IllegalArgumentException("Supplied a null grid to PageBuilder");
		}
		if(FormProcessor.totalValues(sanitisedQuery) != 81) {
			throw new IllegalArgumentException("Invalid Query String");
		}
		try {
			String head = Tags.nest("head", Tags.siblings(HTML.HEAD));
			String body = Tags.nest("body",
			                        Tags.siblings(Tags.nest("header class=\"title\"", HTML.HEADER),
			                                      Tags.nest("div", messageHTML),
			                                      GridBuilder.addGrid(sanitisedQuery),
			                                      Tags.nest("footer", HTML.FOOTER)));
			return Tags.siblings(HTML.DOCTYPE, Tags.nest(HTML.LANG, head, body));
		}
		catch(IllegalArgumentException e) {
			System.out.println("Cannot build grid, Invalid Query String");
			throw e;
		}
	}

}