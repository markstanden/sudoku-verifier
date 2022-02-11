package Grid;

import Grid.Grid;
import Resources.Converters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static Resources.TestGrids.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GridTest
{

	static class BasicGrid extends Grid<Integer>
	{
		public BasicGrid(List<List<Integer>> testGrid, int blockHeight, int blockWidth)
		{
			super(testGrid, blockHeight, blockWidth);
		}
	}



	@Test
	void constructorInvalid_2DArray_NULL()
	{
		assertThrows(NullPointerException.class,
					 () -> new BasicGrid(null, 0, 0),
					 "Provided a null 2d array to grid constructor.  Expected a NullPointerException");
	}


	@Test
	void constructorInvalid_GridSize_Zero()
	{
		assertThrows(IllegalArgumentException.class, () -> new BasicGrid(EMPTY_EMPTY, 1, 1), "Provided a empty grid");
	}


	@Test
	void constructorInvalid_GridSize_OneZero()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(ONE_BY_EMPTY, 1, 1),
					 "Provided a grid with one empty row");
	}


	@Test
	void constructorInvalid_GridSize_EmptyFirstRow()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(MISMATCHED_ROWS_FIRST_EMPTY, 1, 1),
					 "Provided a grid with rows of different lengths, first row is empty");
	}


	@Test
	void constructorInvalid_GridSize_EmptyLastRow()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(MISMATCHED_ROWS_LAST_EMPTY, 1, 1),
					 "Provided a grid with rows of different lengths, last row is empty");
	}


	@Test
	void constructorInvalid_GridSize_MismatchedRow_FirstLarger()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(MISMATCHED_ROWS_FIRST_LARGER, 1, 1),
					 "Provided a grid with rows of different lengths, the first a cell shorter");
	}


	@Test
	void constructorInvalid_GridSize_MismatchedRow_FirstSmaller()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(MISMATCHED_ROWS_FIRST_SMALLER, 1, 1),
					 "Provided a grid with rows of different lengths, the first a cell longer");
	}


	@Test
	void constructorInvalid_BlockSize_ZeroHeight()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(ONE_BY_ONE, 0, 1),
					 "Provided a block size of zero Height");
	}


	@Test
	void constructorInvalid_BlockSize_NegHeight()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(ONE_BY_ONE, - 1, 1),
					 "Provided a block size of negative Height (-1)");
	}


	@Test
	void constructorInvalid_BlockSize_ZeroWidth()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(ONE_BY_ONE, 1, 0),
					 "Provided a block size of zero Width");
	}


	@Test
	void constructorInvalid_BlockSize_NegWidth()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(ONE_BY_ONE, 1, - 1),
					 "Provided a block size of negative Width (-1)");
	}


	@Test
	void constructorInvalid_BlockHeight_TooLarge()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(THREE_BY_THREE, 4, 3),
					 "Provided a block height (4) larger than the height of the supplied grid (3x3)");
	}


	@Test
	void constructorInvalid_BlockWidth_TooLarge()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(THREE_BY_THREE, 3, 4),
					 "Provided a block width (4) larger than the width of the supplied grid (3x3)");
	}


	@Test
	void constructorInvalid_BlockWidth_GridNotCleanlyDivisible()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(THREE_BY_THREE, 2, 2),
					 "Grid.Grid width (3) is not cleanly divisible by block width (2)");
	}


	@Test
	void constructorValid_SizeOne()
	{
		Grid<Integer> testGrid = new BasicGrid(ONE_BY_ONE, 1, 1);
		assertEquals(ONE_BY_ONE,
					 testGrid.getGrid(),
					 "Initialising smallest size grid (1x1) with smallest block size (1x1)");
		assertEquals(ONE_BY_ONE, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(ONE_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(ONE_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");

	}


	@Test
	void constructorValid_SizeTwo_BlockOne()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_TWO, 1, 1);
		assertEquals(TWO_BY_TWO, testGrid.getGrid(), "Initialising grid (2x2) with largest block size (1x1)");
		assertEquals(TWO_BY_TWO, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(TWO_BY_TWO_INVERSE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(FOUR_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");

	}


	@Test
	void constructorValid_SizeTwo_BlockTwo()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_TWO, 2, 2);
		assertEquals(TWO_BY_TWO, testGrid.getGrid(), "Initialising grid (2x2) with largest block size (2x2)");
		assertEquals(TWO_BY_TWO, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(TWO_BY_TWO_INVERSE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(ONE_BY_FOUR,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");
	}


	@Test
	void constructorValid_SizeThree_BlockOne()
	{
		Grid<Integer> testGrid = new BasicGrid(THREE_BY_THREE, 1, 1);
		assertEquals(THREE_BY_THREE, testGrid.getGrid(), "Initialising grid (3x3) with smallest block size (1x1)");
		assertEquals(THREE_BY_THREE,
					 Converters.nestedStreamTo2DList(testGrid.rowStream()),
					 "Row stream not as expected");
		assertEquals(THREE_BY_THREE_INVERSE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(NINE_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");

	}


	@Test
	void constructorInvalid_SizeThree_BlockTwo()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(THREE_BY_THREE, 2, 2),
					 "Grid.Grid width (3) is not cleanly divisible by block width (2)");
	}


	@Test
	void constructorValid_SizeThree_BlockThree()
	{
		Grid<Integer> testGrid = new BasicGrid(THREE_BY_THREE, 3, 3);
		assertEquals(THREE_BY_THREE, testGrid.getGrid(), "Initialising grid (3x3) with largest block size (3x3)");
		assertEquals(THREE_BY_THREE,
					 Converters.nestedStreamTo2DList(testGrid.rowStream()),
					 "Row stream not as expected");
		assertEquals(THREE_BY_THREE_INVERSE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(ONE_BY_NINE,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");
	}


	@Test
	void constructorValid_OneByFour_BlockHeight4()
	{
		Grid<Integer> testGrid = new BasicGrid(ONE_BY_FOUR, 1, 4);
		assertEquals(ONE_BY_FOUR,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (1 row 4 cols) with largest block size (1 row 4 cols)");
		assertEquals(ONE_BY_FOUR, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(FOUR_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(ONE_BY_FOUR,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth1Height1()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_FOUR, 1, 1);
		assertEquals(TWO_BY_FOUR,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (2 row 4 cols) with block size (1 row 1 cols)");
		assertEquals(TWO_BY_FOUR, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(FOUR_BY_TWO,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(EIGHT_BY_ONE,
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth2Height2()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_FOUR, 2, 2);
		assertEquals(TWO_BY_FOUR,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (2 row 4 cols) with block size (2 row 2 cols)");
		assertEquals(TWO_BY_FOUR, Converters.nestedStreamTo2DList(testGrid.rowStream()), "Row stream not as expected");
		assertEquals(FOUR_BY_TWO,
					 Converters.nestedStreamTo2DList(testGrid.colStream()),
					 "Column stream not as expected");
		assertEquals(Converters.arrayTo2DList(new Integer[][]{{1, 2, 5, 6}, {3, 4, 7, 8}}),
					 Converters.nestedStreamTo2DList(testGrid.blockStream()),
					 "block stream not as expected");
	}


	@Test
	void constructorInvalid_TwoByFour_BlockWidth2Height3()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(TWO_BY_FOUR, 3, 2),
					 "Grid.Grid height (4) is not cleanly divisible by block height (3)");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth1Height4()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_FOUR, 1, 4);
		assertEquals(TWO_BY_FOUR,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (2 row 4 cols) with largest block size (1 row 4 cols)");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth2Height4()
	{
		Grid<Integer> testGrid = new BasicGrid(TWO_BY_FOUR, 2, 4);
		assertEquals(TWO_BY_FOUR,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (2 row 4 cols) with largest block size (2 row 4 cols)");
	}


	@Test
	void constructorValid_FourByOne_BlockHeight4()
	{
		Grid<Integer> testGrid = new BasicGrid(FOUR_BY_ONE, 4, 1);
		assertEquals(FOUR_BY_ONE,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (4 row 1 cols) with largest block size (4 row 1 cols)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth1Height1()
	{
		Grid<Integer> testGrid = new BasicGrid(FOUR_BY_TWO, 1, 1);
		assertEquals(FOUR_BY_TWO,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (4 row 2 cols) with block size (1 row 1 cols)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth2Height2()
	{
		Grid<Integer> testGrid = new BasicGrid(FOUR_BY_TWO, 2, 2);
		assertEquals(FOUR_BY_TWO,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (4 row 2 cols) with block size (2 row 2 cols)");
	}


	@Test
	void constructorInvalid_FourByTwo_BlockWidth3Height2()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new BasicGrid(FOUR_BY_TWO, 2, 3),
					 "Grid.Grid width (4) is not cleanly divisible by block width (3)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth1Height4()
	{
		Grid<Integer> testGrid = new BasicGrid(FOUR_BY_TWO, 4, 1);
		assertEquals(FOUR_BY_TWO,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (4 row 2 cols) with largest block size (4 rows 1 col)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth4Height2()
	{
		Grid<Integer> testGrid = new BasicGrid(FOUR_BY_TWO, 4, 2);
		assertEquals(FOUR_BY_TWO,
					 testGrid.getGrid(),
					 "Initialising rectangular grid (4 row 2 cols) with largest block size (4 rows 2 cols)");
	}
}