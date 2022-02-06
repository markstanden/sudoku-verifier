import BaseTests.ConsoleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuVerifierTest extends ConsoleTest {

    public static final String VALID_RESPONSE = "Solution is Valid";
    public static final String INVALID_RESPONSE = "Solution is Invalid";


    @Test
    public void EndToEndSuppliedValidTest() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.VALID);
        sudokuVerifier.verifySolution();
        assertEquals(VALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void EndToEndRegularValidTest() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.REGULAR_VALID);
        sudokuVerifier.verifySolution();
        assertEquals(VALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void EndToEndSuppliedInvalidTest() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidRowsReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID_ROWS);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidColsReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID_COLS);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidBlocksReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.ALL_INVALID_BLOCKS);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidLastValueReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID_LAST);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidFirstValueReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID_FIRST);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }

    @Test
    public void InvalidBlocksValidRowsColsMinimalReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(TestGrids.INVALID_BLOCKS_MINIMAL);
        sudokuVerifier.verifySolution();
        assertEquals(INVALID_RESPONSE, getStrippedConsole());
    }
}