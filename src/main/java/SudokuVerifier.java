import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuVerifier {
    public static final int[] VALID_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int BLOCK_SIZE = 3;

    private final Grid grid;

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
    private boolean gridIsValid() {
        return checkAllRows()
                && checkAllColumns()
                && checkAllBlocks();
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

    private Set<Integer> intStreamToSet(IntStream intStream) {
        return intStream.parallel()
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
        return grid.rowStream()
                .parallel()
                .allMatch(this::checkRow);
    }


    /**
     * Checks individual row for validity.
     *
     * @param row the index of the row to be tested
     * @return true if valid, false if invalid.
     */
    private boolean checkRow(final int row) {
        return checkRow(grid.getRowAsStream(row));
    }


    /**
     * Checks individual row stream for validity.
     *
     * @param row the IntStream of the row to be tested
     * @return true if valid, false if invalid.
     */
    private boolean checkRow(final IntStream row) {
        return isValidSet(
                row.parallel()
                        .boxed()
                        .collect(Collectors.toSet())
        );
    }


    /**
     * Checks all columns for validity.
     * Using parallel streams.
     *
     * @return true if all blocks are valid.
     */
    private boolean checkAllColumns() {
        return grid.colStream()
                .parallel()
                .allMatch(this::checkAColumn);
    }


    /**
     * Checks a single column for validity.
     * Using parallel streams.
     *
     * @param column the index of the column to be verified
     * @return true if all blocks are valid.
     */
    private boolean checkAColumn(final int column) {
        return checkAColumn(grid.getColAsStream(column));
    }


    /**
     * Checks a single column for validity.
     * Using parallel streams.
     *
     * @param column The IntStream of the column to be verified
     * @return true if all blocks are valid.
     */
    private boolean checkAColumn(IntStream column) {
        return isValidSet(
                column.parallel()
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
    private boolean checkAllBlocks() {
        final int NUM_OF_BLOCKS = grid.to2DArray()[0].length / BLOCK_SIZE;

        return IntStream.iterate(0, x -> x + BLOCK_SIZE)
                .limit(NUM_OF_BLOCKS)
                .parallel()
                .allMatch(firstCol -> IntStream.iterate(0, x -> x + BLOCK_SIZE)
                        .limit(NUM_OF_BLOCKS)
                        .parallel()
                        .allMatch(firstRow -> checkABlock(firstRow, firstCol))
                );
    }

    /**
     * Checks the validity of a single block
     *
     * @param firstRow The index of the top row of the block
     * @param firstCol The index of the left hand column of the block
     * @return true if the block is valid
     */
    private boolean checkABlock(final int firstRow, final int firstCol) {
        return isValidSet(
                IntStream.range(firstRow, firstRow + BLOCK_SIZE)
                        .parallel()
                        .mapToObj(blockRow -> Arrays.copyOfRange(grid.to2DArray()[blockRow], firstCol, firstCol + BLOCK_SIZE))
                        .flatMapToInt(blockArray -> Arrays.stream(blockArray))
                        .boxed()
                        .collect(Collectors.toSet())
        );
    }
}
