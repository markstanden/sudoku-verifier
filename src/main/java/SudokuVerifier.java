import java.util.*;
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
        return  rowsAreValid()
                && columnsAreValid()
                && blocksAreValid();
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
    private boolean rowsAreValid() {
        return Stream.of(grid).parallel()                   // Stream<int[]>
                .map(this::arrayToSet)                      // Stream<Set<Integer>>
                .allMatch(this::isValidSet);                // Boolean
    }

    /**
     * Checks all columns for validity.
     *
     * @return true if all blocks are valid.
     */
    private boolean columnsAreValid() {
        return IntStream.range(0, grid[0].length)           // IntStream                Produce indices from 0-8
                .parallel()                                 // IntStream                Many indices at once
                .mapToObj(index -> Arrays.stream(grid)          // Stream<int[]>            Stream of rows
                        .parallel()                             // Stream<int[]>            Many rows at once
                        .mapToInt(row -> row[index])            // IntStream                values at current index
                        .boxed()                                // Stream<Integer>          toSet won't autobox from an IntStream
                        .collect(Collectors.toSet())            // Set<Integer>             Add all the values into a set
                )                                           // Stream<Set<Integer>>     Stream of Sets
                .allMatch(this::isValidSet);                // Boolean                  Validate all the produced sets, only true if All true
    }

    /**
     * Checks each 3x3 Block for validity.
     *
     * @return  true if all blocks are valid.
     */
    private boolean blocksAreValid() {

        // Checks in columns:
        //      A D G
        //      B E H
        //      C F I

        for(int startCol = 0; startCol <= grid[0].length - BLOCK_SIZE; startCol = startCol + BLOCK_SIZE){
            // Break the grid into columns

            Set<Integer> blockSet = new HashSet<>();
            for(int row = 0; row <= grid.length - BLOCK_SIZE; row++){
                // left-hand column of each of the three columns
                // iterate each row

                // Create a copy of the current block line and add to the block set
                Set<Integer> currentLine = arrayToSet(Arrays.copyOfRange(grid[row], startCol, startCol + BLOCK_SIZE));
                blockSet.addAll(currentLine);

                // if the next row index is a multiple of three
                // we have finished the block, so check the set
                if((row + 1) % 3 == 0) {
                    // if block is not valid, exit early
                    if(!isValidSet(blockSet)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
