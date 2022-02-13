package WebView;

import Resources.TestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridBuilderTest
{
	final String TEST_HTML_FORM_TWO_BY_TWO =
			"<form id=\"grid-form\" method=\"post\" action=\"/\">\n"
					+ "    <fieldset class=\"grid-row\" name=\"Row0\" form=\"grid-form\">\n"
					+ "        <span class=\"grid-cell\"><input name=\"R0-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"1\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R0-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"2\"/></span>\n"
					+ "    </fieldset>\n"
					+ "    <fieldset class=\"grid-row\" name=\"Row1\" form=\"grid-form\">\n"
					+ "        <span class=\"grid-cell\"><input name=\"R1-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"3\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R1-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"4\"/></span>\n"
					+ "    </fieldset>\n"
					+ "    <button>Verify</button>\n"
					+ "</form>";

	final String TEST_HTML_FORM_THREE_BY_THREE =
			"<form id=\"grid-form\" method=\"post\" action=\"/\">\n"
					+ "    <fieldset class=\"grid-row\" name=\"Row0\" form=\"grid-form\">\n"
					+ "        <span class=\"grid-cell\"><input name=\"R0-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"1\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R0-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"2\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R0-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"3\"/></span>\n"
					+ "    </fieldset>\n"
					+ "    <fieldset class=\"grid-row\" name=\"Row1\" form=\"grid-form\">\n"
					+ "        <span class=\"grid-cell\"><input name=\"R1-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"4\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R1-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"5\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R1-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"6\"/></span>\n"
					+ "    </fieldset>\n"
					+ "    <fieldset class=\"grid-row\" name=\"Row2\" form=\"grid-form\">\n"
					+ "        <span class=\"grid-cell\"><input name=\"R2-C0\" type=\"number\" min=\"0\" max=\"9\" value=\"7\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R2-C1\" type=\"number\" min=\"0\" max=\"9\" value=\"8\"/></span>\n"
					+ "        <span class=\"grid-cell\"><input name=\"R2-C2\" type=\"number\" min=\"0\" max=\"9\" value=\"9\"/></span>\n"
					+ "    </fieldset>\n"
					+ "    <button>Verify</button>\n"
					+ "</form>";

	@Test
	void createTwoByTwoGridTest()
	{
		assertEquals(TEST_HTML_FORM_TWO_BY_TWO, GridBuilder.addGrid(TestGrids.TWO_BY_TWO), "Provided the two by two test grid");
	}

	@Test
	void createThreeByThreeGridTest()
	{
		assertEquals(TEST_HTML_FORM_THREE_BY_THREE, GridBuilder.addGrid(TestGrids.THREE_BY_THREE), "Provided the three by three test grid");
	}

}