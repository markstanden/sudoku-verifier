package WebView;

public class Tags
{

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


	public static String nest(final String openingTag, final String... html)
	{
		return String.format("<%s>%n%s</%s>",
		                     openingTag,
		                     Tags.siblings(html)
		                         .trim()
		                         .indent(4),
		                     firstWord(openingTag));
	}


	public static String wrap(final String openingTag, final String html)
	{
		return String.format("<%s>%s</%s>", openingTag, html.trim(), firstWord(openingTag));
	}


	public static String createLink(String address, String message, Rel rel, String... classes)
	{
		String classString = classes.length > 0 ? "class=\"" + String.join(" ", classes) : "";
		return "<a " + classString + "\" href=\"" + address + "\" rel=\"" + rel + "\">" + message + "</a>";
	}


	/**
	 * Joins multiple strings together one on each line.
	 * Similar to concat, but strings are joined with a new line character between.
	 * <p>Current indentation is preserved.</p>
	 *
	 * @param siblings
	 *
	 * @return Strings joined with a new line.
	 */
	public static String siblings(String... siblings)
	{
		return String.join("\n", siblings);
	}


	enum Rel
	{
		/** alternate version of the document. */
		ALTERNATE("alternate"),
		/** author of the document. */
		AUTHOR("author"),
		/** URL for bookmarking. */
		BOOKMARK("bookmark"),
		/** downloadable asset. */
		ENCLOSURE("enclosure"),
		/** URL on a different domain. */
		EXTERNAL("external"),
		/** a help document. */
		HELP("help"),
		/** copyright information. */
		LICENSE("license"),
		/** next document */
		NEXT("next"),
		/** for search engines - used for paid links and ads. */
		NOFOLLOW("nofollow"),
		/** don't send an HTTP referrer header. */
		NOREFERRER("noreferrer"),
		/** used when using target="\_blank" on a link. */
		NOOPENER("noopener"),
		/** previous document */
		PREV("prev"),
		/** search function */
		SEARCH("search"),
		/** keyword/tag lookup */
		TAG("tag");

		private final String value;


		Rel(String value)
		{
			this.value = value;
		}


		public String getValue()
		{
			return this.value;
		}
	}
}