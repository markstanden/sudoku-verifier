package WebView;

public class HtmlGenerator
{

	public static final String DOCTYPE = "<!DOCTYPE html>";
	public static final String HTML_OPENING_TAG = "<html lang=\"en\">";
	public static final String HTML_CLOSING_TAG = "</html>";



	public static String nest(final String openingTag, final String html)
	{
		return HtmlGenerator.nest(openingTag, html, openingTag);
	}


	public static String nest(final String openingTag, final String html, final String closingTag)
	{
		return String.format("<%s>%n%s</%s>", openingTag, html.trim().indent(4), closingTag);
	}


	public static String wrap(final String openingTag, final String html, final String closingTag)
	{
		return String.format("<%s>%s</%s>", openingTag, html.trim(), closingTag);
	}

	public static String wrap(final String openingTag, final String html)
	{
		return wrap(openingTag, html, openingTag);
	}


	public static String buildHtml(final String... components)
	{
		String[] result = new String[]{DOCTYPE, HTML_OPENING_TAG, String.join("\n", components), HTML_CLOSING_TAG};
		return String.join("\n", result);
	}
}