import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void fromRealFormDataVALID_REGULAR() {
        Grid testGrid = Grid.fromFormData(SampleFormData.VALID_REGULAR);
        assertArrayEquals(TestGrids.VALID_REGULAR, testGrid.to2DArray());
    }

    @Test
    void fromMockFormDataVALID_REGULAR() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.VALID_REGULAR));
        assertArrayEquals(TestGrids.VALID_REGULAR, testGrid.to2DArray());
    }

    @Test
    void fromMockFormDataVALID() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.VALID));
        assertArrayEquals(TestGrids.VALID, testGrid.to2DArray());
    }

    @Test
    void fromMockFormDataALL_EMPTY() {
        Grid testGrid = Grid.fromFormData(SampleFormData.ALL_EMPTY);
        assertArrayEquals(TestGrids.INVALID_ALL_ZEROES, testGrid.to2DArray());
    }

    @Test
    void fromMockFormDataALL_ZEROES() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.INVALID_ALL_ZEROES));
        assertArrayEquals(TestGrids.INVALID_ALL_ZEROES, testGrid.to2DArray());
    }




    @Test
    void validTo2DArray() {
        Grid grid = new Grid(TestGrids.VALID_REGULAR);
        assertArrayEquals(TestGrids.VALID_REGULAR, grid.to2DArray());
    }
}