import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuVerifier {
    public static final int[] VALID_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int BLOCK_SIZE = 3;

    private final int[][] grid;

    SudokuVerifier(final int[][] grid) {
        this.grid = grid;
    }

    /**
     * Prints a message to the console informing
     * the user whether the grid is valid or not.
     * Prints either:
     * Solution is Valid
     * Solution is Invalid
     * To the console.
     */
    public void verifySolution() {
        System.out.println("Solution is ".concat(gridIsValid() ? "Valid" : "Invalid"));
    }

    /**
     * Checks whether the grid is valid.
     *
     * @return returns true if the supplied grid is valid, false if invalid
     */
    public boolean gridIsValid() {
        return checkAllRows()
                && checkAllColumns()
                && functionalCheckAllBlocks();
    }

    /**
     * The workhorse of the verifier, it converts an
     * array into an unordered set and returns it.
     *
     * @param array The array to convert to a set.
     * @return An unordered set of the contents of the array.
     */
    private Set<Integer> arrayToSet(final int[] array) {
        return Arrays.stream(array)
                .parallel()
                .boxed()
                .collect(Collectors.toSet());
    }

    /**
     * Validates that the group being tested is valid.
     *
     * @param set the set to be tested, whether a row, column or block.
     * @return true if valid, false if invalid.
     */
    private boolean isValidSet(Set<Integer> set) {
        return set.containsAll(arrayToSet(VALID_NUMBERS));
    }

    /**
     * Checks all rows for validity.
     *
     * @return true if valid, false if invalid.
     */
    private boolean checkAllRows() {
        return Stream.of(grid).parallel()
                .map(this::arrayToSet)
                .allMatch(this::isValidSet);
    }

    /**
     * Checks individual row for validity.
     * Not actually used, but included for completeness.
     *
     * @param row the index of the row to be tested
     * @return true if valid, false if invalid.
     */
    private boolean checkRow(final int row) {
        return isValidSet(arrayToSet(grid[row]));
    }

    /**
     * Checks all columns for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean checkAllColumns() {
        return IntStream.range(0, grid[0].length)
                .parallel()
                .allMatch(column -> checkAColumn(column));
    }

    /**
     * Checks a single column for validity.
     * Using parallel streams.
     *
     * @param column the index of the column to be verified
     * @return true if all blocks are valid.
     */
    private boolean checkAColumn(final int column) {
        return isValidSet(
                Arrays.stream(grid)
                        .parallel()
                        .mapToInt(row -> row[column])
                        .boxed()
                        .collect(Collectors.toSet())
        );

    }

    /**
     * Checks all blocks for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean functionalCheckAllBlocks() {
        final int NUM_OF_BLOCKS = grid[0].length / BLOCK_SIZE;

        return IntStream.iterate(0, x -> x + BLOCK_SIZE)
                .limit(NUM_OF_BLOCKS)
                .parallel()
                .allMatch(firstCol -> IntStream.iterate(0, x -> x + BLOCK_SIZE)
                        .limit(NUM_OF_BLOCKS)
                        .parallel()
                        .allMatch(firstRow -> functionalCheckABlock(firstRow, firstCol))
                );
    }

    /**
     * Checks the validity of a single block
     *
     * @param firstRow The index of the top row of the block
     * @param firstCol The index of the left hand column of the block
     * @return true if the block is valid
     */
    private boolean functionalCheckABlock(final int firstRow, final int firstCol) {
        return isValidSet(
                IntStream.range(firstRow, firstRow + BLOCK_SIZE)
                        .parallel()
                        .mapToObj(blockRow -> Arrays.copyOfRange(grid[blockRow], firstCol, firstCol + BLOCK_SIZE))
                        .flatMapToInt(blockArray -> Arrays.stream(blockArray))
                        .boxed()
                        .collect(Collectors.toSet())
        );
    }
}
