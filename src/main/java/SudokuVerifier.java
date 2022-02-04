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
        System.out.println("Solution is ".concat(isValid() ? "Valid" : "Invalid"));
    }

    /**
     * Checks whether the grid is valid.
     *
     * @return returns true if the supplied grid is valid, false if invalid
     */
    private boolean isValid() {
        return rowsAreValid()
                && columnsAreValid()
                && blocksAreValid();
    }

    /**
     * Checks all rows for validity
     *
     * @return true if valid, false if invalid
     */
    private boolean rowsAreValid() {
        return Stream.of(grid).parallel()                   // Stream<int[]>
                .map(this::arrayToSet)                      // Stream<Set<Integer>>
                .allMatch(this::isValidSet);                // Boolean
    }

    /**
     * Converts an array into an unordered set and returns it.
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

    private boolean columnsAreValid() {
        return IntStream.range(0, grid[0].length)           // IntStream
                .parallel()                                 // IntStream
                .mapToObj(index -> Arrays.stream(grid)      // Stream<int[]>
                        .parallel()                         // Stream<int[]>
                        .mapToInt(row -> row[index])        // IntStream
                        .boxed()                            // Stream<Integer>
                        .collect(Collectors.toSet())        // Set<Integer>
                )                                           // Stream<Set<Integer>>
                .allMatch(this::isValidSet);                // Boolean
    }

    /**
     * Checks each 3x3 Block for validity
     * @return  true if all blocks are valid
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

    private boolean isValidSet(Set<Integer> group) {
        //return group.size() == 9;
        return group.containsAll(arrayToSet(VALID_NUMBERS));
    }
}
