import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleFormDataTest {

    @Test
    void createTestString() {
        String testString = SampleFormData.createTestString(TestGrids.VALID_REGULAR);
        assertEquals(SampleFormData.VALID_REGULAR, testString);
    }
}