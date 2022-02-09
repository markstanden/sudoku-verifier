import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class SudokuGrid extends Grid {
    public static final int BLOCK_SIZE = 3;
    public static final int NUM_OF_BLOCKS_PER_ROW = 3;
    public static final int GRID_LENGTH = 9;

    public static final String WEBFORM_REGEX = "R[0-8]-C[0-8]=([0-9]?)(?=&|$)";

    public SudokuGrid(int[][] arrayData) {
        super(arrayData, SudokuGrid.BLOCK_SIZE);
    }

    /**
     * Return a new grid object from supplied form data.
     * Assumes passed string not sanitised and will return null if string is not
     * in expected format.
     *
     * @param formQuery the data string received from the front end form request.
     *                  this should progress left to right, top to bottom.
     *                  R0-C0=0&R0-C1=0&...
     * @return new Grid object
     */
    public static SudokuGrid fromFormData(String formQuery) {
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


        int[][] out = IntStream.range(0, GRID_LENGTH)
                .mapToObj(rowStart ->
                        Arrays.copyOfRange(safe, SudokuGrid.GRID_LENGTH * rowStart, SudokuGrid.GRID_LENGTH * (rowStart + 1))
                )
                .toArray(int[][]::new);

        return new SudokuGrid(out);
    }
}
