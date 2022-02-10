import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.descriptor.AbstractTestDescriptor;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest
{

	class TestGrid extends Grid
	{
		public TestGrid(int[][] testGrid, int blockHeight, int blockWidth)
		{
			super(testGrid, blockHeight, blockWidth);
		}

	}


	final int[][] ONE_BY_ONE = new int[][]{{1}};
	final int[][] ONE_BY_FOUR = new int[][]{{1, 2, 3, 4}};
	final int[][] ONE_BY_NINE = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}};

	final int[][] TWO_BY_TWO = new int[][]{{1, 2}, {3, 4}};
	final int[][] TWO_BY_TWO_INVERSE = new int[][]{{1, 3}, {2, 4}};
	final int[][] TWO_BY_FOUR = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};

	final int[][] THREE_BY_THREE = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	final int[][] THREE_BY_THREE_INVERSE = new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

	final int[][] FOUR_BY_ONE = new int[][]{{1}, {2}, {3}, {4}};
	final int[][] FOUR_BY_TWO = new int[][]{{1, 5}, {2, 6}, {3, 7}, {4, 8}};

	final int[][] EIGHT_BY_ONE = new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}};
	final int[][] NINE_BY_ONE = new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}};



	@Test
	void constructorInvalid_2DArray_NULL()
	{
		assertThrows(NullPointerException.class,
					 () -> new TestGrid(null, 0, 0),
					 "Provided a null 2d array to grid constructor.  Expected a NullPointerException");
	}


	@Test
	void constructorInvalid_BlockSize_ZeroHeight()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(ONE_BY_ONE, 0, 1),
					 "Provided a block size of zero Height");
	}


	@Test
	void constructorInvalid_BlockSize_NegHeight()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(ONE_BY_ONE, - 1, 1),
					 "Provided a block size of negative Height (-1)");
	}


	@Test
	void constructorInvalid_BlockSize_ZeroWidth()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(ONE_BY_ONE, 1, 0),
					 "Provided a block size of zero Width");
	}


	@Test
	void constructorInvalid_BlockSize_NegWidth()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(ONE_BY_ONE, 1, - 1),
					 "Provided a block size of negative Width (-1)");
	}


	@Test
	void constructorInvalid_BlockHeight_TooLarge()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(THREE_BY_THREE, 4, 3),
					 "Provided a block height (4) larger than the height of the supplied grid (3x3)");
	}


	@Test
	void constructorInvalid_BlockWidth_TooLarge()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(THREE_BY_THREE, 3, 4),
					 "Provided a block width (4) larger than the width of the supplied grid (3x3)");
	}


	@Test
	void constructorInvalid_BlockWidth_GridNotCleanlyDivisible()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}}, 2, 2),
					 "Grid width (3) is not cleanly divisible by block width (2)");
	}


	@Test
	void constructorValid_SizeOne()
	{
		Grid testGrid = new TestGrid(ONE_BY_ONE, 1, 1);
		assertArrayEquals(ONE_BY_ONE,
						  testGrid.as2DArray(),
						  "Initialising smallest size grid (1x1) with smallest block size (1x1)");
		assertArrayEquals(ONE_BY_ONE,
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(ONE_BY_ONE,
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(ONE_BY_ONE,
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");

	}


	@Test
	void constructorValid_SizeTwo_BlockOne()
	{
		Grid testGrid = new TestGrid(TWO_BY_TWO, 1, 1);
		assertArrayEquals(TWO_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising grid (2x2) with largest block size (1x1)");
		assertArrayEquals(TWO_BY_TWO,
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(TWO_BY_TWO_INVERSE,
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(FOUR_BY_ONE,
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");

	}


	@Test
	void constructorValid_SizeTwo_BlockTwo()
	{
		Grid testGrid = new TestGrid(TWO_BY_TWO, 2, 2);
		assertArrayEquals(TWO_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising grid (2x2) with largest block size (2x2)");
		assertArrayEquals(TWO_BY_TWO,
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(TWO_BY_TWO_INVERSE,
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(ONE_BY_FOUR,
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");
	}


	@Test
	void constructorValid_SizeThree_BlockOne()
	{
		Grid testGrid = new TestGrid(THREE_BY_THREE, 1, 1);
		assertArrayEquals(THREE_BY_THREE,
						  testGrid.as2DArray(),
						  "Initialising grid (3x3) with smallest block size (1x1)");
		assertArrayEquals(THREE_BY_THREE,
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(THREE_BY_THREE_INVERSE,
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(Arrays.stream(NINE_BY_ONE).toArray(),
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");

	}


	@Test
	void constructorInvalid_SizeThree_BlockTwo()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}}, 2, 2),
					 "Grid width (3) is not cleanly divisible by block width (2)");
	}


	@Test
	void constructorValid_SizeThree_BlockThree()
	{
		Grid testGrid = new TestGrid(THREE_BY_THREE, 3, 3);
		assertArrayEquals(THREE_BY_THREE,
						  testGrid.as2DArray(),
						  "Initialising grid (3x3) with largest block size (3x3)");
		assertArrayEquals(Arrays.stream(THREE_BY_THREE).toArray(),
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(Arrays.stream(THREE_BY_THREE_INVERSE).toArray(),
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(Arrays.stream(ONE_BY_NINE).toArray(),
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");
	}


	@Test
	void constructorValid_OneByFour_BlockHeight4()
	{
		Grid testGrid = new TestGrid(ONE_BY_FOUR, 1, 4);
		assertArrayEquals(ONE_BY_FOUR,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (1 row 4 cols) with largest block size (1 row 4 cols)");
		assertArrayEquals(Arrays.stream(ONE_BY_FOUR).toArray(),
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(Arrays.stream(FOUR_BY_ONE).toArray(),
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(Arrays.stream(ONE_BY_FOUR).toArray(),
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth1Height1()
	{
		Grid testGrid = new TestGrid(TWO_BY_FOUR, 1, 1);
		assertArrayEquals(TWO_BY_FOUR,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (2 row 4 cols) with block size (1 row 1 cols)");
		assertArrayEquals(Arrays.stream(TWO_BY_FOUR).toArray(),
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(Arrays.stream(FOUR_BY_TWO).toArray(),
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(Arrays.stream(EIGHT_BY_ONE).toArray(),
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth2Height2()
	{
		Grid testGrid = new TestGrid(TWO_BY_FOUR, 2, 2);
		assertArrayEquals(TWO_BY_FOUR,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (2 row 4 cols) with block size (2 row 2 cols)");
		assertArrayEquals(TWO_BY_FOUR,
						  testGrid.rowStream().map(IntStream::toArray).toArray(),
						  "Row stream not as expected");
		assertArrayEquals(FOUR_BY_TWO,
						  testGrid.colStream().map(IntStream::toArray).toArray(),
						  "Column stream not as expected");
		assertArrayEquals(new int[][]{{1,2,5,6},{3,4,7,8}},
						  testGrid.blockStream().map(IntStream::toArray).toArray(),
						  "block stream not as expected");
	}


	@Test
	void constructorInvalid_TwoByFour_BlockWidth2Height3()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(TWO_BY_FOUR, 3, 2),
					 "Grid height (4) is not cleanly divisible by block height (3)");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth1Height4()
	{
		Grid testGrid = new TestGrid(TWO_BY_FOUR, 1, 4);
		assertArrayEquals(TWO_BY_FOUR,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (2 row 4 cols) with largest block size (1 row 4 cols)");
	}


	@Test
	void constructorValid_TwoByFour_BlockWidth2Height4()
	{
		Grid testGrid = new TestGrid(TWO_BY_FOUR, 2, 4);
		assertArrayEquals(TWO_BY_FOUR,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (2 row 4 cols) with largest block size (2 row 4 cols)");
	}


	@Test
	void constructorValid_FourByOne_BlockHeight4()
	{
		Grid testGrid = new TestGrid(FOUR_BY_ONE, 4, 1);
		assertArrayEquals(FOUR_BY_ONE,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (4 row 1 cols) with largest block size (4 row 1 cols)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth1Height1()
	{
		Grid testGrid = new TestGrid(FOUR_BY_TWO, 1, 1);
		assertArrayEquals(FOUR_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (4 row 2 cols) with block size (1 row 1 cols)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth2Height2()
	{
		Grid testGrid = new TestGrid(FOUR_BY_TWO, 2, 2);
		assertArrayEquals(FOUR_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (4 row 2 cols) with block size (2 row 2 cols)");
	}


	@Test
	void constructorInvalid_FourByTwo_BlockWidth3Height2()
	{
		assertThrows(IllegalArgumentException.class,
					 () -> new TestGrid(FOUR_BY_TWO, 2, 3),
					 "Grid width (4) is not cleanly divisible by block width (3)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth1Height4()
	{
		Grid testGrid = new TestGrid(FOUR_BY_TWO, 4, 1);
		assertArrayEquals(FOUR_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (4 row 2 cols) with largest block size (4 rows 1 col)");
	}


	@Test
	void constructorValid_FourByTwo_BlockWidth4Height2()
	{
		Grid testGrid = new TestGrid(FOUR_BY_TWO, 4, 2);
		assertArrayEquals(FOUR_BY_TWO,
						  testGrid.as2DArray(),
						  "Initialising rectangular grid (4 row 2 cols) with largest block size (4 rows 2 cols)");
	}
}