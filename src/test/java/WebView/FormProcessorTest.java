package WebView;

import Resources.SudokuTestGrids;
import Resources.TestGrids;
import Resources.WebQueries;
import org.junit.jupiter.api.Test;

import static WebView.FormProcessor.validateNumstringToList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormProcessorTest
{

	@Test
	void listFromString_QueryTooShort()
	{

		assertThrows(IllegalArgumentException.class,
					 () -> validateNumstringToList(WebQueries.IS_80_INTS_LONG),
					 "Supplied a String of ints from 0-9 80 characters long");
	}


	@Test
	void listFromString_NullQuery()
	{
		assertThrows(NullPointerException.class, () -> validateNumstringToList(null), "Supplied a null query string");
	}


	@Test
	void listFromString_QueryTooLong()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> validateNumstringToList(WebQueries.IS_80_INTS_LONG.concat("12")),
					 "Supplied a String of ints from 0-9 82 characters long");
	}


	@Test
	void listFromString_QueryContainsNonInts_First()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> validateNumstringToList("A".concat(WebQueries.IS_80_INTS_LONG)),
					 "Supplied an 'A' followed by ints from 0-9 80 characters long");
	}


	@Test
	void listFromString_QueryContainsNonInts_Last()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> validateNumstringToList(WebQueries.IS_80_INTS_LONG.concat("Z")),
					 "Supplied ints from 0-9 80 characters long, followed by a 'Z'");
	}


	@Test
	void listFromString_ValidQuery_InvalidRows()
	{
		assertEquals(SudokuTestGrids.INVALID_ROWS,
					 validateNumstringToList(WebQueries.INVALID_ROWS),
					 "Supplied ints from 0-9 81 characters long, should match Invalid Rows Grid");
	}


	@Test
	void listFromString_ValidQuery_InvalidCols()
	{
		assertEquals(SudokuTestGrids.INVALID_COLS,
					 validateNumstringToList(WebQueries.INVALID_COLS),
					 "Supplied ints from 0-9 81 characters long, should match Invalid Cols Grid");
	}


	@Test
	void listFromString_ValidQuery_ValidRegular()
	{
		assertEquals(SudokuTestGrids.VALID_REGULAR,
					 validateNumstringToList(WebQueries.VALID_REGULAR),
					 "Supplied ints from 0-9 81 characters long, should match Valid Regular Grid");
	}


	@Test
	void validateFormDataToList()
	{
	}


	@Test
	void nestedListToStringTest()
	{
		assertEquals(WebQueries.INVALID_ROWS,
					 FormProcessor.nestedListToString(SudokuTestGrids.INVALID_ROWS),
					 "Invalid Rows");
		assertEquals(WebQueries.INVALID_COLS,
					 FormProcessor.nestedListToString(SudokuTestGrids.INVALID_COLS),
					 "Invalid Cols");
		assertEquals(WebQueries.VALID_REGULAR,
					 FormProcessor.nestedListToString(SudokuTestGrids.VALID_REGULAR),
					 "Valid Regular");
	}


	@Test
	void totalValues_SudokuGrids()
	{
		assertEquals(81, FormProcessor.totalValues(SudokuTestGrids.VALID_REGULAR), "Valid Regular");
		assertEquals(81, FormProcessor.totalValues(SudokuTestGrids.INVALID_ROWS), "Invalid Rows");
		assertEquals(81, FormProcessor.totalValues(SudokuTestGrids.INVALID_COLS), "Invalid Cols");
		assertEquals(81, FormProcessor.totalValues(SudokuTestGrids.ALL_ZEROES), "All Zeroes");
	}


	@Test
	void totalValues_OtherGrids()
	{
		assertEquals(8, FormProcessor.totalValues(TestGrids.FOUR_BY_TWO), "4x2");
		assertEquals(8, FormProcessor.totalValues(TestGrids.TWO_BY_FOUR), "2x4");
		assertEquals(9, FormProcessor.totalValues(TestGrids.THREE_BY_THREE), "3x3");
		assertEquals(4, FormProcessor.totalValues(TestGrids.TWO_BY_TWO), "2x2");
		assertEquals(1, FormProcessor.totalValues(TestGrids.ONE_BY_ONE), "1x1");
		assertEquals(0, FormProcessor.totalValues(TestGrids.EMPTY_EMPTY), "0x0");
	}
}