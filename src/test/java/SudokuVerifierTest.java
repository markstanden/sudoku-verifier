import BaseTests.ConsoleTest;
import Resources.SudokuTestGrids;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuVerifierTest extends ConsoleTest {

    @Test
    public void EndToEndSuppliedValidTest() {
        assertTrue(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.VALID_REAL)));
    }

    @Test
    public void EndToEndRegularValidTest() {
        assertTrue(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.VALID_REGULAR)));
    }

    @Test
    public void EndToEndSuppliedInvalidTest() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID)));
    }

    @Test
    public void InvalidRowsReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_ROWS)));
    }

    @Test
    public void InvalidColsReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_COLS)));
    }

    @Test
    public void InvalidBlocksReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.ALL_INVALID_BLOCKS)));
    }

    @Test
    public void InvalidValidBlocksInvalidRowsAndColsReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_REGULAR_BLOCKS)));
    }

    @Test
    public void InvalidLastValueReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_LAST)));
    }

    @Test
    public void InvalidFirstValueReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_FIRST)));
    }

    @Test
    public void InvalidBlocksValidRowsColsMinimalReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.INVALID_BLOCKS_MINIMAL)));
    }

    @Test
    public void AllZeroesReturnsInvalid() {
        assertFalse(SudokuVerifier.verify(new SudokuGrid(SudokuTestGrids.ALL_ZEROES)));
    }
}