import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuVerifier {
    public static final Set<Integer> VALID_NUMBERS = arrayToSet(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

    private final Grid grid;

    /**
     * Private constructor to force use of
     * verify static method to create object.
     *
     * @param solution The solution to verify
     */
    private SudokuVerifier(final Grid solution) {
        this.grid = solution;
    }

    /**
     * Static builder to reduce boilerplate when verifying a grid
     *
     * @param grid The sudoku grid to be verified
     * @return true if the grid is valid, false if invalid
     */
    public static boolean verify(Grid grid) {
        SudokuVerifier verifier = new SudokuVerifier(grid);
        return verifier.gridIsValid();
    }

    /**
     * Prints a message to the console informing
     * the user whether the grid is valid or not.
     * Prints to the console either:
     * Solution is Valid
     * Solution is Invalid
     */
    public void verifySolution() {
        System.out.println("Solution is ".concat(gridIsValid() ? "Valid" : "Invalid"));
    }

    /**
     * Checks whether the grid is valid.
     * Collects and parallel checks all rows, columns and blocks.
     *
     * @return returns true if the supplied grid is valid, false if invalid
     */
    private boolean gridIsValid() {
        return Stream.of(grid.rowStream(), grid.colStream(), grid.blockStream())
                .flatMap(x -> x)
                .parallel()
                .allMatch(this::checkAGroup);
    }

    /**
     * Converts an array into an unordered set and returns it.
     *
     * @param array The array to convert to a set.
     * @return An unordered set of the contents of the array.
     */
    private static Set<Integer> arrayToSet(final int[] array) {
        return Arrays.stream(array)
                .parallel()
                .boxed()
                .collect(Collectors.toSet());
    }


    /**
     * Checks all rows for validity.
     *
     * @return true if valid, false if invalid.
     */
    private boolean checkAllRows() {
        return groupStreamIsValid(grid.rowStream());
    }


    /**
     * Checks all columns for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean checkAllColumns() {
        return groupStreamIsValid(grid.colStream());
    }

    /**
     * Checks all blocks for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean checkAllBlocks() {
        return groupStreamIsValid(grid.blockStream());
    }


    /**
     * Checks a stream for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean groupStreamIsValid(Stream<IntStream> groupStream) {
        return groupStream
                .parallel()
                .allMatch(this::checkAGroup);
    }


    /**
     * Converts an IntStream into an unordered set for comparison
     *
     * @param intStream The IntStream to collect the values from
     * @return An unordered set of the values from the stream.
     */
    private Set<Integer> intStreamToSet(IntStream intStream) {
        return intStream.parallel()
                .boxed()
                .collect(Collectors.toSet());
    }

    /**
     * Checks individual group stream (row, column, block) for validity.
     *
     * @param group the IntStream of the row to be tested
     * @return true if valid, false if invalid.
     */
    private boolean checkAGroup(final IntStream group) {
        return intStreamToSet(group).containsAll(VALID_NUMBERS);
    }

}
