import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

	private final T[][] grid;


	/**
	 * @param grid2dArray
	 * 		array of T
	 * @param blockHeight
	 * @param blockWidth
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
	public Grid(final T[][] grid2dArray, final int blockHeight, final int blockWidth) throws NullPointerException
	{
		Objects.requireNonNull(grid2dArray, "The grid supplied cannot be null");

		GRID_HEIGHT = grid2dArray.length;
		if(GRID_HEIGHT < 1) {
			throw new IllegalArgumentException(String.format("Supplied grid height (%d) is zero (No rows found)",
															 GRID_HEIGHT));
		}

		GRID_WIDTH = grid2dArray[0].length;
		for(T[] row : grid2dArray) {
			if(row.length < 1) {
				throw new IllegalArgumentException(String.format("Supplied grid has row of length zero"));
			}
			else if(row.length != GRID_WIDTH) {
				throw new IllegalArgumentException(String.format("Supplied grid has rows of uneven length"));
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


		this.grid = grid2dArray;

		BLOCKS_PER_ROW = GRID_WIDTH / BLOCK_WIDTH;
		BLOCKS_PER_COLUMN = GRID_HEIGHT / BLOCK_HEIGHT;
	}


	/**
	 * Provided for backwards compatibility, returns a
	 * 2D array of the grid in the form of an array of rows
	 * with each row an array of cell values.
	 *
	 * @return A 2D array representation of the grid.
	 */
	public T[][] as2DArray()
	{
		return grid;
	}


	/**
	 * Produces an ordered stream to represent the row.
	 *
	 * @return An IntStream of sequential cell values
	 * 		representing the row
	 * 		The IntStream progresses left to right.
	 */
	public Stream<T> getRowAsStream(int rowNumber)
	{
		return Arrays.stream(grid[rowNumber]);
	}


	/**
	 * Produces a stream of row streams to represent the grid.
	 *
	 * @return A Stream of sequential IntStreams
	 * 		representing the grid.
	 * 		Streams progress from top row to bottom row,
	 * 		with each row progressing left to right.
	 * 		<p>
	 * 		<pre>
	 * 																																																		Row Order    ==>     Row 'index'         <br/>
	 * 																																																		RowA                 0 1 2 3 4 5...      <br/>
	 * 																																																		RowB                 0 1 2 3 4 5...      <br/>
	 * 																																																		RowC                 0 1 2 3 4 5...      <br/>
	 * 																																																		RowD                 0 1 2 3 4 5...      <br/>
	 * 																																																		<pre>
	 */
	public Stream<Stream<T>> rowStream()
	{
		return Stream.of(grid)
				.sequential()
				.map(Arrays::stream);
	}


	/**
	 * Produces a stream to represent the column.
	 *
	 * @return An IntStream of sequential cell values
	 * 		representing the column
	 * 		The IntStream progresses top to bottom.
	 */
	public Stream<T> getColAsStream(int column)
	{
		return Arrays.stream(grid)
				.sequential()
				.map(row -> row[column]);
	}


	/**
	 * Produces an ordered stream of column streams to represent the grid.
	 * Each column is an IntStream (essentially a Stream&lt;int&gt;)
	 *
	 * @return A Stream of sequential IntStreams
	 * 		representing the grid.
	 * 		Streams progress from left column to right column,
	 * 		with each column starting at the top.
	 * 		<pre>
	 * 						Column:     A   B   C   D   E   F       <br/>
	 * 						Order:      1   6   11  16  21  26      <br/>
	 * 						 			2   7   12  17  22  27      <br/>
	 * 									3   8   13  18  23  28      <br/>
	 * 									4   9   14  19  24  29      <br/>
	 * 									5   10  15  20  25  30      <br/>
	 * 					</pre>
	 */
	public Stream<Stream<T>> colStream()
	{
		return IntStream.range(0, grid[0].length)
				.sequential()
				.mapToObj(this::getColAsStream);
	}


	/**
	 * Produces a stream to represent the Block.
	 *
	 * @return An IntStream of sequential cell values
	 * 		representing the column
	 * 		The IntStream progresses left to right, top to bottom.
	 * 		<p>
	 * 		<pre>
	 * 						1st row, cells 1 to 3    ===>    A  B  C     <br/>
	 * 						2nd row, cells 4 to 6    ===>    D  E  F     <br/>
	 * 						3rd row, cells 7 to 9    ===>    G  H  I     <br/>
	 * 						</pre>
	 */
	public Stream<T> getBlockAsStream(final int firstRow, final int firstCol)
	{
		return IntStream.range(firstRow, firstRow + BLOCK_HEIGHT).sequential().mapToObj(blockRow -> Arrays.copyOfRange(
				grid[blockRow],
				firstCol,
				firstCol + BLOCK_WIDTH)).flatMap(Arrays::stream);
	}


	/**
	 * Produces a stream of block streams to represent the grid.
	 *
	 * @return A Stream of sequential IntStreams
	 * 		representing each block on the grid.
	 * 		Streams progress left to right, top to bottom.
	 * 		<p>
	 * 		<pre>
	 * 										1st to 3rd Blocks    ===>    A  B  C     <br/>
	 * 										4th to 6th Blocks    ===>    D  E  F     <br/>
	 * 										7th to 9th Blocks    ===>    G  H  I     <br/>
	 * 										</pre>
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