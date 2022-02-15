package WebView;

public class HTML
{
	public static final String DOCTYPE = "<!DOCTYPE html>";
	public static final String LANG = "html lang=\"en\"";

	public static final String HEAD = Tags.siblings("<meta charset=\"UTF-8\">",
	                                                "<link rel=\"icon\" href=\"data:,\">",
	                                                Tags.wrap("title", "Sudoku Verifier"),
	                                                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css\">");
	public static final String HEADER = Tags.siblings(Tags.wrap("h1", "The Sudoku Verifier"),
	                                                  Tags.wrap("p class=\"author\"", "By Mark Standen"));
	public static final String FOOTER = Tags.siblings("By Mark Standen, 2022.");

}