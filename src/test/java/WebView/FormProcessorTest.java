package WebView;

import Resources.SudokuTestGrids;
import Resources.WebQueries;
import org.junit.jupiter.api.Test;

import static WebView.FormProcessor.listFromNumString;
import static org.junit.jupiter.api.Assertions.*;

class FormProcessorTest
{

	@Test
	void listFromString_QueryTooShort()
	{

		assertThrows(IllegalArgumentException.class,
					 () -> listFromNumString(WebQueries.IS_80_INTS_LONG),
					 "Supplied a String of ints from 0-9 80 characters long");
	}

	@Test
	void listFromString_NullQuery()
	{
		assertThrows(NullPointerException.class,
					 () -> listFromNumString(null),
					 "Supplied a null query string");
	}


	@Test
	void listFromString_QueryTooLong()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> listFromNumString(WebQueries.IS_80_INTS_LONG.concat("12")),
					 "Supplied a String of ints from 0-9 82 characters long");
	}


	@Test
	void listFromString_QueryContainsNonInts_First()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> listFromNumString("A".concat(WebQueries.IS_80_INTS_LONG)),
					 "Supplied an 'A' followed by ints from 0-9 80 characters long");
	}


	@Test
	void listFromString_QueryContainsNonInts_Last()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> listFromNumString(WebQueries.IS_80_INTS_LONG.concat("Z")),
					 "Supplied ints from 0-9 80 characters long, followed by a 'Z'");
	}


	@Test
	void listFromString_ValidQuery_InvalidRows()
	{
		assertEquals(SudokuTestGrids.INVALID_ROWS, listFromNumString(WebQueries.INVALID_ROWS),
					 "Supplied ints from 0-9 81 characters long, should match Invalid Rows Grid");
	}


	@Test
	void listFromString_ValidQuery_InvalidCols()
	{
		assertEquals(SudokuTestGrids.INVALID_COLS, listFromNumString(WebQueries.INVALID_COLS),
					 "Supplied ints from 0-9 81 characters long, should match Invalid Cols Grid");
	}


	@Test
	void listFromString_ValidQuery_ValidRegular()
	{
		assertEquals(SudokuTestGrids.VALID_REGULAR, listFromNumString(WebQueries.VALID_REGULAR),
					 "Supplied ints from 0-9 81 characters long, should match Valid Regular Grid");
	}

}