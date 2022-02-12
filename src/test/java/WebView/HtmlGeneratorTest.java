package WebView;

import Resources.TestGrids;
import org.junit.jupiter.api.Test;

import static WebView.HtmlGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlGeneratorTest
{
	final String TEST_PARA = "<p>\n    Test\n</p>";

	@Test
	void wrapWithSameOpeningClosing()
	{
		assertEquals("<div>\n    <p>\n        Test\n    </p>\n</div>", nest("div", TEST_PARA));
	}


	@Test
	void wrapWithCustomOpeningClosingTag()
	{

		assertEquals("<div class=\"test\">\n    <p>\n        Test\n    </p>\n</div>",
					 nest("div class=\"test\"", TEST_PARA, "div"));
	}


	@Test
	void buildHtmlWithNoComponents()
	{
		assertEquals(String.format("%s%n%s%n%s%n%s", DOCTYPE, HTML_OPENING_TAG, "", HTML_CLOSING_TAG), buildHtml());
	}


	@Test
	void buildHtmlWithOneComponent()
	{
		assertEquals(String.format("%s%n%s%n%s%n%s", DOCTYPE, HTML_OPENING_TAG, TEST_PARA, HTML_CLOSING_TAG),
					 buildHtml(TEST_PARA));
	}


	@Test
	void buildHtmlWithThreeComponents()
	{
		assertEquals(String.format("%s%n%s%n%s%n%s%n%s%n%s",
								   DOCTYPE,
								   HTML_OPENING_TAG,
								   TEST_PARA,
								   TEST_PARA,
								   TEST_PARA,
								   HTML_CLOSING_TAG), buildHtml(TEST_PARA, TEST_PARA, TEST_PARA));
	}


	@Test
	void buildHtmlWithFiveComponents()
	{
		assertEquals(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s",
								   DOCTYPE,
								   HTML_OPENING_TAG,
								   TEST_PARA,
								   TEST_PARA,
								   TEST_PARA,
								   TEST_PARA,
								   TEST_PARA,
								   HTML_CLOSING_TAG), buildHtml(TEST_PARA, TEST_PARA, TEST_PARA, TEST_PARA, TEST_PARA));
	}
}