package Sudoku;

import Grid.Grid;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridTest
{

	final IntStream[] ARRAY_OF_ROW_STREAM = new IntStream[]{
			Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
			Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
			Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
			Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
			Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
			Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
			Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
			Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
			Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6})
	};

	final Stream<IntStream> ROW_STREAM_INVALID_COLS = Arrays.stream(ARRAY_OF_ROW_STREAM);

	final Stream<IntStream> BLOCK_STREAM_INVALID_BLOCKS_MINIMAL = Arrays.stream(new IntStream[]{
			Arrays.stream(new int[]{2, 4, 1, 7, 3, 5, 8, 6, 9}),
			Arrays.stream(new int[]{6, 9, 5, 4, 2, 8, 7, 3, 1}),
			Arrays.stream(new int[]{3, 8, 7, 1, 6, 9, 4, 2, 5}),
			Arrays.stream(new int[]{4, 1, 8, 6, 9, 2, 5, 8, 7}),
			Arrays.stream(new int[]{3, 7, 9, 5, 1, 3, 2, 4, 6}),
			Arrays.stream(new int[]{2, 5, 6, 7, 4, 8, 9, 3, 1}),
			Arrays.stream(new int[]{1, 7, 3, 9, 5, 4, 3, 2, 6}),
			Arrays.stream(new int[]{8, 6, 4, 1, 8, 2, 9, 5, 7}),
			Arrays.stream(new int[]{5, 9, 2, 6, 7, 3, 8, 1, 4}),
			});


	@Test
	void constructorNULL()
	{
		assertThrows(NullPointerException.class, () -> new SudokuGrid(null));
	}


	@Test
	void toRowStream()
	{

		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_COLS);
		assertEquals(ROW_STREAM_INVALID_COLS.flatMapToInt(x -> x).boxed().toList(),
					 testGrid.rowStream()
							 .flatMap(x -> x)
							 .toList());
	}


	@Test
	void toColStream()
	{
		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_ROWS);
		assertEquals(ROW_STREAM_INVALID_COLS.flatMapToInt(x -> x).boxed().toList(),
					 testGrid.colStream().flatMap(x -> x).toList());
	}


	@Test
	void toBlockStream()
	{

		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_BLOCKS_MINIMAL);
		assertArrayEquals(BLOCK_STREAM_INVALID_BLOCKS_MINIMAL.flatMapToInt(x -> x).boxed().toArray(),
						  testGrid.blockStream().flatMap(x -> x).toArray());
	}


	@Test
	void rowAsStream()
	{
		IntStream[] testStream = new IntStream[]{
				Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
				Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
				Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
				Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
				Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
				Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
				Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
				Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
				Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6})
		};
		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_COLS);
		IntStream.range(0, 9)
				.forEach(row -> assertArrayEquals(testStream[row].boxed().toArray(),
												  testGrid.getRowAsStream(row).toArray(),
												  "Row Number: ".concat(String.valueOf(row)))
						);
	}


	@Test
	void colAsStream()
	{
		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_ROWS);
		IntStream.range(0, 9)
				.forEach(col -> assertArrayEquals(ARRAY_OF_ROW_STREAM[col].boxed().toArray(),
												  testGrid.getColAsStream(col).toArray(),
												  "Column Number: ".concat(String.valueOf(col)))
						);
	}


	@Test
	void blockAsStream()
	{
		Grid<Integer> testGrid = new SudokuGrid(SudokuTestGrids.INVALID_BLOCKS_MINIMAL);
		assertArrayEquals(new Integer[]{2, 4, 1, 7, 3, 5, 8, 6, 9}, testGrid.getBlockAsStream(0, 0).toArray());
		assertArrayEquals(new Integer[]{6, 9, 5, 4, 2, 8, 7, 3, 1}, testGrid.getBlockAsStream(0, 3).toArray());
		assertArrayEquals(new Integer[]{3, 8, 7, 1, 6, 9, 4, 2, 5}, testGrid.getBlockAsStream(0, 6).toArray());
		assertArrayEquals(new Integer[]{4, 1, 8, 6, 9, 2, 5, 8, 7}, testGrid.getBlockAsStream(3, 0).toArray());
		assertArrayEquals(new Integer[]{3, 7, 9, 5, 1, 3, 2, 4, 6}, testGrid.getBlockAsStream(3, 3).toArray());
		assertArrayEquals(new Integer[]{2, 5, 6, 7, 4, 8, 9, 3, 1}, testGrid.getBlockAsStream(3, 6).toArray());
		assertArrayEquals(new Integer[]{1, 7, 3, 9, 5, 4, 3, 2, 6}, testGrid.getBlockAsStream(6, 0).toArray());
		assertArrayEquals(new Integer[]{8, 6, 4, 1, 8, 2, 9, 5, 7}, testGrid.getBlockAsStream(6, 3).toArray());
		assertArrayEquals(new Integer[]{5, 9, 2, 6, 7, 3, 8, 1, 4}, testGrid.getBlockAsStream(6, 6).toArray());
	}
}