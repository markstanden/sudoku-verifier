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
     * 
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
                && functionalCheckAllBlocks();  // Functional implementation using streams
        //      && checkAllBlocks();            // Classic Java implementation using loops
    }

    /**
     * The workhorse of the verifier, it converts an
     * array into an unordered set and returns it.
     *
     * @param array The array to convert to a set.
     * @return An unordered set of the contents of the array.
     */
    private Set<Integer> arrayToSet(final int[] array) {
        return Arrays.stream(array)                         // IntStream
                .parallel()                                 // IntStream
                .boxed()                                    // Stream<Integer>
                .collect(Collectors.toSet());               // Set<Integer>
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
        return Stream.of(grid).parallel()                   // Stream<int[]>
                .map(this::arrayToSet)                      // Stream<Set<Integer>>
                .allMatch(this::isValidSet);                // Boolean
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
        return IntStream.range(0, grid[0].length)               // IntStream        Produce indices from 0-8
                .parallel()                                     // IntStream        Many indices at once
                .allMatch(column -> checkAColumn(column));      // Boolean          Validate each column, only true if all true
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
                Arrays.stream(grid)                     // Stream<int[]>            Stream of rows
                        .parallel()                             // Stream<int[]>            Many rows at once
                        .mapToInt(row -> row[column])           // IntStream                values at current index
                        .boxed()                                // Stream<Integer>          toSet won't autobox from an IntStream
                        .collect(Collectors.toSet())            // Set<Integer>             Add all the values into a set
        );                                              // Boolean                  True if column is valid.

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
                .limit(NUM_OF_BLOCKS)                                                        // IntStream            // indices of starting columns
                .parallel()                                                                  // IntStream            // many indices of starting columns
                .allMatch(firstCol -> IntStream.iterate(0, x -> x + BLOCK_SIZE)         // IntStream            // 0, 3, 6, 9, 12
                        .limit(NUM_OF_BLOCKS)                                                // IntStream            // indices of starting rows (0, 3, 6)
                        .parallel()                                                          // IntStream            // many indices of starting rows
                        .allMatch(firstRow -> functionalCheckABlock(firstRow, firstCol))     // Stream<boolean>      // Will fail early if any block within column fails test
                );                                                                           // boolean              // Will fail early if any column fails test
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
                IntStream.range(firstRow, firstRow + BLOCK_SIZE)                            // IntStream            // indices block rows 0, 1, 2
                        .parallel()                                                         // IntStream            // many indices of block rows
                        .mapToObj(blockRow -> Arrays.copyOfRange(grid[blockRow], firstCol, firstCol + BLOCK_SIZE))        // Stream<int[]>    // Create a copy of the block row
                        .flatMapToInt(blockArray -> Arrays.stream(blockArray))              // IntStream            // Converts all the mini arrays into a new IntStream
                        .boxed()                                                            // Stream<Integer>      // Collectors won't take an IntStream :-(
                        .collect(Collectors.toSet())                                        // Set<Integer>         // Add all to a block set
        );
    }

    /**
     * Checks all blocks for validity.
     * Using classic java for loops
     *
     * @return true if all blocks are valid.
     */
    private boolean checkAllBlocks() {
        // Checks in columns:
        //      A D G
        //      B E H
        //      C F I

        // Break the grid into columns
        for (int firstCol = 0; firstCol <= grid[0].length - BLOCK_SIZE; firstCol = firstCol + BLOCK_SIZE) {
            // startCol = 0, 3, 6

            // Break the columns into rows
            for (int firstRow = 0; firstRow <= grid.length - BLOCK_SIZE; firstRow = firstRow + BLOCK_SIZE) {
                // firstRow = 0, 3, 6
                // grid[firstRow][firstCol] is top left corner of each block within grid

                // check each block, any failures should fail test
                if (!checkABlock(firstRow, firstCol)) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Checks a single block for validity.
     * Using classic java for loops
     *
     * @return true if the block is valid.
     */
    private boolean checkABlock(final int firstRow, final int firstCol) {
        //create an empty set for the block
        final Set<Integer> blockSet = new HashSet<>();

        for (int blockRow = firstRow; blockRow < firstRow + BLOCK_SIZE; blockRow++) {
            // left-hand column of each of the three columns
            // iterate each row of block
            // e.g. blockRow = 6,7,8

            // Create a copy of the current block line and add to the block set
            Set<Integer> currentLine = arrayToSet(Arrays.copyOfRange(grid[blockRow], firstCol, firstCol + BLOCK_SIZE));
            blockSet.addAll(currentLine);
        }
        return isValidSet(blockSet);
    }
}
