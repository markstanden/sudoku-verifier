package WebView;

public class Tags
{

	public static final String DOCTYPE = "<!DOCTYPE html>";
	public static final String HTML_OPENING_TAG = "<html lang=\"en\">";
	public static final String HTML_CLOSING_TAG = "</html>";


	public static String firstWord(final String phrase)
	{
		final String start = phrase.stripLeading();
		final int wordEnd = start.indexOf(" ");
		final int lineEnd = start.indexOf("\n");

		// indexOf returns -1 for a not found
		final int spaceIndex = wordEnd < 0 ? start.length() : wordEnd;
		final int lineIndex = lineEnd < 0 ? start.length() : lineEnd;

		return start.substring(0, Math.min(spaceIndex, lineIndex));
	}


	public static String nest(final String openingTag, final String html)
	{
		String closingTag = firstWord(openingTag);
		return String.format("<%s>%n%s</%s>",
							 openingTag,
							 html.trim()
								 .indent(4),
							 closingTag);
	}


	public static String wrap(final String openingTag, final String html)
	{
		String closingTag = firstWord(openingTag);
		return String.format("<%s>%s</%s>", openingTag, html.trim(), closingTag);
	}


	public static String buildHtml(final String... components)
	{
		String[] result = new String[]{DOCTYPE, HTML_OPENING_TAG, String.join("\n", components), HTML_CLOSING_TAG};
		return String.join("\n", result);
	}
}