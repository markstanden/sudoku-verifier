package WebView;

import org.junit.jupiter.api.Test;

import static WebView.Tags.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TagsTest
{
	final String TEST_PARA = "<p>\n    Test\n</p>";


	@Test
	void firstWord_SingleWord()
	{
		assertEquals("div", firstWord("div"));
	}


	@Test
	void firstWord_Empty()
	{
		assertEquals("", firstWord(""));
	}


	@Test
	void firstWord_LineBreak()
	{
		assertEquals("<p>", firstWord("<p>\n    Test\n</p>"));
	}


	@Test
	void firstWord_Space()
	{
		assertEquals("div", firstWord("div class=\"test\""));
	}


	@Test
	void nestWithSameOpeningClosing()
	{
		assertEquals("<div>\n    <p>\n        Test\n    </p>\n</div>", nest("div", TEST_PARA));
	}


	@Test
	void nestWithCustomOpeningTag()
	{

		assertEquals("<div class=\"test\">\n    <p>\n        Test\n    </p>\n</div>",
		             nest("div class=\"test\"", TEST_PARA));
	}


	@Test
	void wrapWithSameOpeningClosing()
	{
		assertEquals("<span><p>Test</p></span>", wrap("span", "<p>Test</p>"));
	}


	@Test
	void wrapWithCustomOpeningTag()
	{

		assertEquals("<span class=\"test\"><p>Test</p></span>", wrap("span class=\"test\"", "<p>Test</p>"));
	}



	@Test
	void buildHtmlWithOneComponent()
	{
		assertEquals(String.format("%s%n%s%n%s%n%s",
		                           HTML.DOCTYPE,
		                           "<html lang=\"en\">",
		                           "    <p>\n        Test\n    </p>",
		                           "</html>"), Tags.siblings(HTML.DOCTYPE, Tags.nest(HTML.LANG, TEST_PARA)));
	}


	@Test
	void buildHtmlWithThreeComponents()
	{
		String indentedPara = "    <p>\n        Test\n    </p>";
		assertEquals(String.format("%s%n%s%n%s%n%s%n%s%n%s",
		                           HTML.DOCTYPE,
		                           "<html lang=\"en\">",
		                           indentedPara,
		                           indentedPara,
		                           indentedPara,
		                           "</html>"),
		             Tags.siblings(HTML.DOCTYPE, Tags.nest(HTML.LANG, TEST_PARA, TEST_PARA, TEST_PARA)));
	}
}