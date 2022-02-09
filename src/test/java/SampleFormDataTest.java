import Resources.SampleFormData;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the function used to create mock form data for testing purposes.
 */
class SampleFormDataTest {

    @Test
    void createTestString() {
        String testString = SampleFormData.createTestString(SudokuTestGrids.VALID_REGULAR);
        assertEquals(SampleFormData.VALID_REGULAR, testString);
    }
}