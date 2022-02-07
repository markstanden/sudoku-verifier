import BaseTests.ConsoleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuVerifierTest extends ConsoleTest {

    @Test
    public void EndToEndSuppliedValidTest() {
        assertTrue(SudokuVerifier.verify(new Grid(TestGrids.VALID)));
    }

    @Test
    public void EndToEndRegularValidTest() {
        assertTrue(SudokuVerifier.verify(new Grid(TestGrids.VALID_REGULAR)));
    }

    @Test
    public void EndToEndSuppliedInvalidTest() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID)));
    }

    @Test
    public void InvalidRowsReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_ROWS)));
    }

    @Test
    public void InvalidColsReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_COLS)));
    }

    @Test
    public void InvalidBlocksReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.ALL_INVALID_BLOCKS)));
    }

    @Test
    public void InvalidLastValueReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_LAST)));
    }

    @Test
    public void InvalidFirstValueReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_FIRST)));
    }

    @Test
    public void InvalidBlocksValidRowsColsMinimalReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_BLOCKS_MINIMAL)));
    }

    @Test
    public void AllZeroesReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new Grid(TestGrids.INVALID_ALL_ZEROES)));
    }
}