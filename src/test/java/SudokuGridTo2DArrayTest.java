import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridTo2DArrayTest {
    @Test
    void to2DArrayVALID_REGULAR() {
        Grid testGrid = new SudokuGrid(SudokuTestGrids.VALID_REGULAR);
        assertArrayEquals(SudokuTestGrids.VALID_REGULAR, testGrid.as2DArray());
    }

    @Test
    void to2DArrayVALID_REAL() {
        Grid testGrid = new SudokuGrid(SudokuTestGrids.VALID_REAL);
        assertArrayEquals(SudokuTestGrids.VALID_REAL, testGrid.as2DArray());
    }

    @Test
    void to2DArrayALL_ZEROES() {
        Grid testGrid = new SudokuGrid(SudokuTestGrids.ALL_ZEROES);
        assertArrayEquals(SudokuTestGrids.ALL_ZEROES, testGrid.as2DArray());
    }
}
