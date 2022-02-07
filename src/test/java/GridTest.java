import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void fromFormData() {

    }

    @Test
    void validTo2DArray() {
        Grid grid = new Grid(TestGrids.VALID_REGULAR);
        assertArrayEquals(TestGrids.VALID_REGULAR, grid.to2DArray());
    }
}