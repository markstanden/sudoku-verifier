import Resources.SampleFormData;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SudokuGridFromFormDataTest {
    @Test
    void RealDataVALID_REGULAR() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.VALID_REGULAR);
        assertArrayEquals(SudokuTestGrids.VALID_REGULAR, testGrid.as2DArray());
    }

    @Test
    void MockDataVALID_REGULAR() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.createTestString(SudokuTestGrids.VALID_REGULAR));
        assertArrayEquals(SudokuTestGrids.VALID_REGULAR, testGrid.as2DArray());
    }

    @Test
    void MockDataVALID() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.createTestString(SudokuTestGrids.VALID_REAL));
        assertArrayEquals(SudokuTestGrids.VALID_REAL, testGrid.as2DArray());
    }

    @Test
    void MockDataALL_EMPTY() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.ALL_EMPTY);
        assertArrayEquals(SudokuTestGrids.ALL_ZEROES, testGrid.as2DArray());
    }

    @Test
    void MockDataALL_ZEROES() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.createTestString(SudokuTestGrids.ALL_ZEROES));
        assertArrayEquals(SudokuTestGrids.ALL_ZEROES, testGrid.as2DArray());
    }

}