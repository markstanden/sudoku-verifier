package WebView;

import Resources.TestGrids;
import org.junit.jupiter.api.Test;

import static WebView.HtmlGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlGeneratorTest
{
	final String TEST_PARA = "<p>\n    Test\n</p>";

	final String TEST_HTML_FORM_TWO_BY_TWO =
			"<form id=\"grid-form\" method=\"post\" action=\"\">\n"
					+ "    <div class=\"grid-full\">\n"
					+ "        <fieldset class=\"grid-row\" name=\"Row0\" form=\"grid-form\">\n"
					+ "            <span class=\"grid-cell\"><input name=\"R0-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"1\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R0-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"2\"/></span>\n"
					+ "        </fieldset>\n"
					+ "        <fieldset class=\"grid-row\" name=\"Row1\" form=\"grid-form\">\n"
					+ "            <span class=\"grid-cell\"><input name=\"R1-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"3\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R1-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"4\"/></span>\n"
					+ "        </fieldset>\n"
					+ "    </div>\n"
					+ "    <button class=\"verify\">Verify</button>\n"
					+ "    <button class=\"reset\">Reset</button>\n"
					+ "</form>";

	final String TEST_HTML_FORM_THREE_BY_THREE =
			"<form id=\"grid-form\" method=\"post\" action=\"\">\n"
					+ "    <div class=\"grid-full\">\n"
					+ "        <fieldset class=\"grid-row\" name=\"Row0\" form=\"grid-form\">\n"
					+ "            <span class=\"grid-cell\"><input name=\"R0-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"1\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R0-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"2\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R0-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"3\"/></span>\n"
					+ "        </fieldset>\n"
					+ "        <fieldset class=\"grid-row\" name=\"Row1\" form=\"grid-form\">\n"
					+ "            <span class=\"grid-cell\"><input name=\"R1-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"4\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R1-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"5\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R1-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"6\"/></span>\n"
					+ "        </fieldset>\n"
					+ "        <fieldset class=\"grid-row\" name=\"Row2\" form=\"grid-form\">\n"
					+ "            <span class=\"grid-cell\"><input name=\"R2-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"7\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R2-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"8\"/></span>\n"
					+ "            <span class=\"grid-cell\"><input name=\"R2-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"9\"/></span>\n"
					+ "        </fieldset>\n"
					+ "    </div>\n"
					+ "    <button class=\"verify\">Verify</button>\n"
					+ "    <button class=\"reset\">Reset</button>\n"
					+ "</form>";


	@Test
	void createTwoByTwoGridTest()
	{
		assertEquals(TEST_HTML_FORM_TWO_BY_TWO, createGrid(TestGrids.TWO_BY_TWO), "Provided the two by two test grid");
	}

	@Test
	void createThreeByThreeGridTest()
	{
		assertEquals(TEST_HTML_FORM_THREE_BY_THREE, createGrid(TestGrids.THREE_BY_THREE), "Provided the three by three test grid");
	}


	@Test
	void wrapWithSameOpeningClosing()
	{
		assertEquals("<div>\n    <p>\n        Test\n    </p>\n</div>\n", wrap("div", TEST_PARA));
	}


	@Test
	void wrapWithCustomOpeningClosingTag()
	{

		assertEquals("<div class=\"test\">\n    <p>\n        Test\n    </p>\n</div>\n",
					 wrap("div class=\"test\"", TEST_PARA, "div"));
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