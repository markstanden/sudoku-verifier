package Grid;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Encapsulate the storage of the grid to hide its
 * implementation from the classes using it.
 * The grid class produces sequential streams representing elements of the grid.
 *
 * @author Mark Standen
 * @version 1.0.0
 */
public abstract class Grid<T>
{
	public final int GRID_WIDTH;
	public final int GRID_HEIGHT;

	public final int BLOCK_WIDTH;
	public final int BLOCK_HEIGHT;

	public final int BLOCKS_PER_ROW;
	public final int BLOCKS_PER_COLUMN;

	private final List<List<T>> grid;


	/**
	 * @param grid
	 * 		array of T
	 * @param blockHeight
	 * 		The height of the sub-blocks within the grid
	 * @param blockWidth
	 * 		The width of the sub-blocks within the grid
	 *
	 * @throws NullPointerException
	 * 		Throws if the passed 2DArray is null
	 * @throws IllegalArgumentException
	 * 		Throws if a zero row array {} or zero column array {{}} is passed.
	 * 		Throws if row lengths are unequal.
	 * 		Throws if the block height or block width is zero.
	 * 		Throws if grid height (number of rows) is not cleanly divisible by the block height.
	 * 		Throws if grid width (row length) is not cleanly divisible by the block width.
	 */
	public Grid(final List<List<T>> grid, final int blockHeight, final int blockWidth) throws NullPointerException
	{
		Objects.requireNonNull(grid, "The grid supplied cannot be null");

		GRID_HEIGHT = grid.size();
		if(GRID_HEIGHT < 1) {
			throw new IllegalArgumentException(String.format("Supplied grid height (%d) is zero (No rows found)",
															 GRID_HEIGHT));
		}

		GRID_WIDTH = grid.get(0).size();
		for(List<T> row : grid) {
			if(row.size() < 1) {
				throw new IllegalArgumentException("Supplied grid has row of length zero");
			}
			else if(row.size() != GRID_WIDTH) {
				throw new IllegalArgumentException("Supplied grid has rows of uneven length");
			}
		}

		BLOCK_WIDTH = blockWidth;
		BLOCK_HEIGHT = blockHeight;

		if(BLOCK_HEIGHT <= 0 || BLOCK_WIDTH <= 0) {
			throw new IllegalArgumentException(String.format(
					"Supplied block width (%d) or height (%d) is less than or equal to zero",
					BLOCK_WIDTH,
					BLOCK_HEIGHT));
		}

		if(GRID_WIDTH % BLOCK_WIDTH != 0) {
			throw new IllegalArgumentException(String.format(
					"Supplied block width (%d) does fit cleanly into supplied array row (%d)",
					BLOCK_WIDTH,
					GRID_WIDTH));
		}

		if(GRID_HEIGHT % BLOCK_HEIGHT != 0) {
			throw new IllegalArgumentException(String.format(
					"Supplied block height (%d) does fit cleanly into supplied array columns (%d)",
					BLOCK_HEIGHT,
					GRID_HEIGHT));
		}


		this.grid = grid;

		BLOCKS_PER_ROW = GRID_WIDTH / BLOCK_WIDTH;
		BLOCKS_PER_COLUMN = GRID_HEIGHT / BLOCK_HEIGHT;
	}





	/**
	 * Returns the grid as nested 2D lists.
	 *
	 * @return The 2D List representation of the grid.
	 */
	public List<List<T>> getGrid()
	{
		return grid;
	}


	/**
	 * Produces an ordered stream to represent the row.
	 *
	 * @return A Stream of sequential cell values
	 * 		representing the row
	 * 		The Stream progresses left to right.
	 */
	public Stream<T> getRowAsStream(int rowNumber)
	{
		return grid.get(rowNumber).stream();
	}


	/**
	 * Produces a stream of row streams to represent the grid.
	 *
	 * @return A Stream of sequential Streams
	 * 		representing the grid.
	 * 		Streams progress from top row to bottom row,
	 * 		with each row progressing left to right.
	 * 		<p>
	 * 		<pre>
	 * 																																																								Row Order    ==>     Row 'index'         <br/>
	 * 																																																								RowA                 0 1 2 3 4 5...      <br/>
	 * 																																																								RowB                 0 1 2 3 4 5...      <br/>
	 * 																																																								RowC                 0 1 2 3 4 5...      <br/>
	 * 																																																								RowD                 0 1 2 3 4 5...      <br/>
	 * 																																																								<pre>
	 */
	public Stream<Stream<T>> rowStream()
	{
		return grid.stream()
				.sequential()
				.map(Collection::stream);
	}


	/**
	 * Produces a stream to represent the column.
	 *
	 * @return A Stream of sequential cell values
	 * 		representing the column
	 * 		The Stream progresses top to bottom.
	 */
	public Stream<T> getColAsStream(int column)
	{
		return grid.stream()
				.sequential()
				.map(row -> row.get(column));
	}


	/**
	 * Produces an ordered stream of column streams to represent the grid.
	 * Each column is a Stream&lt;T&gt;
	 *
	 * @return A Stream of sequential Streams
	 * 		representing the grid.
	 * 		Streams progress from left column to right column,
	 * 		with each column starting at the top.
	 * 		<pre>
	 * 												Column:     A   B   C   D   E   F       <br/>
	 * 												Order:      1   6   11  16  21  26      <br/>
	 * 												 			2   7   12  17  22  27      <br/>
	 * 															3   8   13  18  23  28      <br/>
	 * 															4   9   14  19  24  29      <br/>
	 * 															5   10  15  20  25  30      <br/>
	 * 											</pre>
	 */
	public Stream<Stream<T>> colStream()
	{
		return IntStream.range(0, GRID_WIDTH)
				.sequential()
				.mapToObj(this::getColAsStream);
	}


	/**
	 * Produces a stream to represent the Block.
	 *
	 * @return A Stream of sequential cell values
	 * 		representing the column
	 * 		The Stream progresses left to right, top to bottom.
	 * 		<p>
	 * 		<pre>
	 * 												1st row, cells 1 to 3    ===>    A  B  C     <br/>
	 * 												2nd row, cells 4 to 6    ===>    D  E  F     <br/>
	 * 												3rd row, cells 7 to 9    ===>    G  H  I     <br/>
	 * 												</pre>
	 */
	public Stream<T> getBlockAsStream(final int firstRow, final int firstCol)
	{
		return IntStream.range(firstRow, firstRow + BLOCK_HEIGHT)
				.sequential()
				.mapToObj(blockRow -> grid.get(blockRow).subList(firstCol, firstCol + BLOCK_WIDTH))
				.flatMap(Collection::stream);
	}


	/**
	 * Produces a stream of block streams to represent the grid.
	 *
	 * @return A Stream of sequential Streams
	 * 		representing each block on the grid.
	 * 		Streams progress left to right, top to bottom.
	 * 		<p>
	 * 		<pre>
	 * 																1st to 3rd Blocks    ===>    A  B  C     <br/>
	 * 																4th to 6th Blocks    ===>    D  E  F     <br/>
	 * 																7th to 9th Blocks    ===>    G  H  I     <br/>
	 * 																</pre>
	 */
	public Stream<Stream<T>> blockStream()
	{
		return IntStream.range(0, BLOCKS_PER_COLUMN)
				.sequential()
				.mapToObj(firstRow -> IntStream.range(0, BLOCKS_PER_ROW)
						.mapToObj(
								firstCol -> getBlockAsStream(firstRow * BLOCK_HEIGHT,
															 firstCol * BLOCK_WIDTH))).flatMap(x -> x);
	}
}