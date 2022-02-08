import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Encapsulate the storage of the grid to hide its
 * implementation from the classes using it.
 *
 * @author Mark Standen
 * @version 0.0.1
 */
public class Grid {
    private final int[][] grid;

    public Grid(int[][] arrayData) {
        this.grid = arrayData;
    }

    /**
     * Return a new grid object from supplied form data.
     * Assumes passed string not sanitised and will return null if string is not
     * in expected format.
     *
     * @param formData the data string received from the front end form request.
     *                 this should progress left to right, top to bottom.
     *                 R0-C0=0&R0-C1=0&...
     * @return new Grid object
     */
    public static Grid fromFormData(String formData) {
        Pattern p = Pattern.compile("^R[0-8]-C[0-8]=([0-9]?)$");

        String[] unsafeString = formData.split("&");
        int[] safe = Arrays.stream(unsafeString)
                .filter(cellValue -> cellValue.matches(p.pattern()))
                .map(value -> {
                    Matcher m = p.matcher(value);
                    return m.results()
                            .map(matchResult -> matchResult.group(1))
                            .map(match -> match.isBlank() ? "0" : match)
                            .findFirst()
                            .orElse("0");
                })
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
     * @return  A 2D array representation of the grid.
     *
     */
    public int[][] to2DArray() {
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
     * Streams progress left to right, top to bottom.
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
     * Streams progress top to bottom, left to right.
     */
    public Stream<IntStream> colStream(){
        return IntStream.range(0, grid[0].length)
                .sequential()
                .mapToObj(this::getColAsStream);
    }
}
