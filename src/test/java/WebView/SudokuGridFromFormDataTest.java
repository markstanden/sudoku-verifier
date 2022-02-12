package WebView;

import Converters.Converters;
import Resources.SampleFormData;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuGridFromFormDataTest {
    @Test
    void RealDataVALID_REGULAR() {
        List<List<Integer>> testData = FormProcessor.validateFormDataToList(SampleFormData.VALID_REGULAR);
        assertEquals(SudokuTestGrids.VALID_REGULAR, testData);
    }

    @Test
    void MockDataVALID_REGULAR() {
        List<List<Integer>> testData = FormProcessor.validateFormDataToList(Converters.mockFormDataFromTestGrid(SudokuTestGrids.VALID_REGULAR));
        assertEquals(SudokuTestGrids.VALID_REGULAR, testData);
    }

    @Test
    void MockDataVALID() {
        List<List<Integer>> testData = FormProcessor.validateFormDataToList(Converters.mockFormDataFromTestGrid(SudokuTestGrids.VALID_REAL));
        assertEquals(SudokuTestGrids.VALID_REAL, testData);
    }

    @Test
    void MockDataALL_EMPTY() {
        List<List<Integer>> testData = FormProcessor.validateFormDataToList(SampleFormData.ALL_EMPTY);
        assertEquals(SudokuTestGrids.ALL_ZEROES, testData);
    }

    @Test
    void MockDataALL_ZEROES() {
        List<List<Integer>> testData = FormProcessor.validateFormDataToList(Converters.mockFormDataFromTestGrid(SudokuTestGrids.ALL_ZEROES));
        assertEquals(SudokuTestGrids.ALL_ZEROES, testData);
    }

}