import Resources.Converters;
import Resources.SampleFormData;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuGridFromFormDataTest {
    @Test
    void RealDataVALID_REGULAR() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.VALID_REGULAR);
        assertEquals(SudokuTestGrids.VALID_REGULAR, testGrid.getGrid());
    }

    @Test
    void MockDataVALID_REGULAR() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(Converters.mockFormDataFromTestGrid(SudokuTestGrids.VALID_REGULAR));
        assertEquals(SudokuTestGrids.VALID_REGULAR, testGrid.getGrid());
    }

    @Test
    void MockDataVALID() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(Converters.mockFormDataFromTestGrid(SudokuTestGrids.VALID_REAL));
        assertEquals(SudokuTestGrids.VALID_REAL, testGrid.getGrid());
    }

    @Test
    void MockDataALL_EMPTY() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(SampleFormData.ALL_EMPTY);
        assertEquals(SudokuTestGrids.ALL_ZEROES, testGrid.getGrid());
    }

    @Test
    void MockDataALL_ZEROES() {
        Grid<Integer> testGrid = SudokuGrid.fromFormData(Converters.mockFormDataFromTestGrid(SudokuTestGrids.ALL_ZEROES));
        assertEquals(SudokuTestGrids.ALL_ZEROES, testGrid.getGrid());
    }

}