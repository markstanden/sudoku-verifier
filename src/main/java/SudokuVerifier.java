import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuVerifier {
    public static final int[] VALID_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final int[][] grid;

    SudokuVerifier(final int[][] grid) {
        this.grid = grid;
    }

    public void verifySolution() {
        System.out.println("Solution is ".concat(isValid() ? "Valid" : "Invalid"));
    }

    /**
     * Checks whether the grid is valid
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
                .allMatch(this::isValidSet);                  // Boolean
    }

    private boolean blocksAreValid() {
        return true;
    }

    private boolean isValidSet(Set<Integer> group) {
        return group.size() == 9;
    }
}
