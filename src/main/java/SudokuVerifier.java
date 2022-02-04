import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuVerifier {
    public static final int[] VALID_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final int[][] grid;

    SudokuVerifier(final int[][] grid) {
        this.grid = grid;
    }

    public boolean isValid() {
        return checkAllRows()
                && checkAllColumns()
                && checkAllBlocks();
    }

    private boolean checkAllRows() {
        return Arrays.stream(grid)
                .parallel()
                .allMatch(this::checkGroup);
    }

    private boolean checkAllColumns() {
        //boolean test = Arrays.stream(grid)
        return true;
    }

    private boolean checkAllBlocks() {
        return true;
    }

    private boolean checkGroup(int[] group) {
        return Arrays.stream(group)
                .parallel()
                .boxed()
                .collect(Collectors.toSet())
                .size() == 9;
    }
}
