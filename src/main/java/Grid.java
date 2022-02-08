import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class Grid {
    public final int BLOCK_SIZE = 3;
    public final int NUM_OF_BLOCKS_PER_ROW;

    public static final String WEBFORM_REGEX = "R[0-8]-C[0-8]=([0-9]?)(?=&|$)";

    private final int[][] grid;

    public Grid(int[][] arrayData) {
        this.grid = arrayData;
        NUM_OF_BLOCKS_PER_ROW = grid[0].length / BLOCK_SIZE;
    }

    /**
     * Return a new grid object from supplied form data.
     * Assumes passed string not sanitised and will return null if string is not
     * in expected format.
     *
     * @param formQuery the data string received from the front end form request.
     *                 this should progress left to right, top to bottom.
     *                 R0-C0=0&R0-C1=0&...
     * @return new Grid object
     */
    public static Grid fromFormData(String formQuery) {
        Pattern regexPattern = Pattern.compile(WEBFORM_REGEX);
        Matcher regexMatcher = regexPattern.matcher(formQuery);

        int[] safe = regexMatcher.results()
                .map(matchResult -> matchResult.group(1))

                /*  The webform can have empty values,
                these have been matched by the regex expression,
                but empty values will break the grid, so replace with 0.  */
                .map(match -> match.isBlank() ? "0" : match)
                .mapToInt(Integer::valueOf)
                .toArray();


        int[][] out = IntStream.range(0, 9)
                .mapToObj(row ->
                        Arrays.copyOfRange(safe, 9 * row, 9 * (row + 1))
                )
                .toArray(int[][]::new);

        return new Grid(out);
    }


    /**
     * Provided for backwards compatibility, returns a
     * 2D array of the grid in the form of an array of rows
     * with each row an array of cell values.
     *
     * @return A 2D array representation of the grid.
     */
    public int[][] as2DArray() {
        return grid;
    }


    /**
     * Produces a stream to represent the row.
     *
     * @return An IntStream of sequential cell values
     * representing the row
     * The IntStream progresses left to right.
     */
    public IntStream getRowAsStream(int rowNumber) {
        return Arrays.stream(grid[rowNumber]);
    }


    /**
     * Produces a stream of row streams to represent the grid.
     *
     * @return A Stream of sequential IntStreams
     * representing the grid.
     * Streams progress from top row to bottom row,
     * with each row progressing left to right.
     * <p>
     * Row      Row 'index'
     * Order
     * A       0 1 2 3 4 5
     * B       0 1 2 3 4 5
     * C       0 1 2 3 4 5
     * D       0 1 2 3 4 5
     */
    public Stream<IntStream> rowStream() {
        return Stream.of(grid)
                .sequential()
                .map(Arrays::stream);
    }


    /**
     * Produces a stream to represent the column.
     *
     * @return An IntStream of sequential cell values
     * representing the column
     * The IntStream progresses top to bottom.
     */
    public IntStream getColAsStream(int column) {
        return Arrays.stream(grid)
                .sequential()
                .mapToInt(row -> row[column]);
    }


    /**
     * Produces a stream of column streams to represent the grid.
     *
     * @return A Stream of sequential IntStreams
     * representing the grid.
     * Streams progress from left column to right column,
     * with each column starting at the top.
     * <p>
     * Column Order:       A B C D E F...
     * Column 'index':     0 0 0 0 0 0
     * 1 1 1 1 1 1
     * 2 2 2 2 2 2
     * etc.
     */
    public Stream<IntStream> colStream() {
        return IntStream.range(0, grid[0].length)
                .sequential()
                .mapToObj(this::getColAsStream);
    }


    /**
     * Produces a stream to represent the Block.
     *
     * @return An IntStream of sequential cell values
     * representing the column
     * The IntStream progresses left to right, top to bottom.
     * <p>
     * 1st row, cells 1 to 3 ----> A B C
     * 2nd row, cells 4 to 6 ----> D E F
     * 3rd row, cells 7 to 9 ----> G H I
     */
    public IntStream getBlockAsStream(final int firstRow, final int firstCol) {
        return IntStream.range(firstRow, firstRow + BLOCK_SIZE)
                .sequential()
                .mapToObj(blockRow -> Arrays.copyOfRange(grid[blockRow], firstCol, firstCol + BLOCK_SIZE))
                .flatMapToInt(Arrays::stream);
    }


    /**
     * Produces a stream of column streams to represent the grid.
     *
     * @return A Stream of sequential IntStreams
     * representing the grid.
     * Streams progress left to right, top to bottom.
     * <p>
     * 1st to 3rd Blocks ----> A B C
     * 4th to 6th Blocks ----> D E F
     * 7th to 9th Blocks ----> G H I
     */
    public Stream<IntStream> blockStream() {
        return IntStream.range(0, NUM_OF_BLOCKS_PER_ROW)
                .sequential()
                .mapToObj(firstRow -> IntStream.range(0, NUM_OF_BLOCKS_PER_ROW)
                        .mapToObj(firstCol -> getBlockAsStream(firstRow * BLOCK_SIZE, firstCol * BLOCK_SIZE))
                ).flatMap(x -> x);  // Merges Stream<Stream> -> Stream
    }
}
