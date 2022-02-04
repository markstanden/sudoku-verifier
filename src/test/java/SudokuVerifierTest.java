import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SudokuVerifierTest {

    public static final int[][] VALID = {   {2, 4, 1, 6, 9, 5, 3, 8, 7},
                                            {7, 3, 5, 4, 2, 8, 1, 6, 9},
                                            {8, 6, 9, 7, 3, 1, 4, 2, 5},
                                            {4, 1, 3, 8, 7, 9, 2, 5, 6},
                                            {6, 9, 2, 5, 1, 3, 7, 4, 8},
                                            {5, 8, 7, 2, 4, 6, 9, 3, 1},
                                            {1, 7, 8, 3, 6, 4, 5, 9, 2},
                                            {9, 5, 4, 1, 8, 2, 6, 7, 3},
                                            {3, 2, 6, 9, 5, 7, 8, 1, 4}};

    public static final int[][] INVALID = { {2, 4, 1, 6, 9, 5, 3, 8, 1},
                                            {7, 3, 5, 4, 2, 8, 1, 6, 9},
                                            {8, 6, 9, 7, 3, 1, 4, 2, 5},
                                            {4, 1, 3, 8, 7, 9, 2, 5, 6},
                                            {6, 9, 2, 5, 2, 3, 7, 4, 8},
                                            {5, 8, 7, 2, 4, 6, 9, 3, 1},
                                            {1, 7, 8, 3, 6, 4, 5, 9, 2},
                                            {8, 5, 4, 1, 8, 2, 6, 7, 3},
                                            {3, 2, 6, 9, 5, 7, 8, 1, 4}};

    public static final int[][] INVALID_COLS = {{1, 2, 3, 4, 5, 6, 7, 8, 9},
                                                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                                                {8, 6, 9, 1, 2, 3, 4, 5, 6},
                                                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                                                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                                                {8, 6, 9, 1, 2, 3, 4, 5, 6},
                                                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                                                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                                                {8, 6, 9, 1, 2, 3, 4, 5, 6}};

    public static final int[][] INVALID_ROWS = {{1, 4, 7, 1, 4, 7, 1, 4, 7},
                                                {2, 5, 8, 2, 5, 8, 2, 5, 8},
                                                {3, 6, 9, 3, 6, 9, 3, 6, 9},
                                                {4, 7, 1, 4, 7, 1, 4, 7, 1},
                                                {5, 8, 2, 5, 8, 2, 5, 8, 2},
                                                {6, 9, 3, 6, 9, 3, 6, 9, 3},
                                                {7, 1, 4, 7, 1, 4, 7, 1, 4},
                                                {8, 2, 5, 8, 2, 5, 8, 2, 5},
                                                {9, 3, 6, 9, 3, 6, 9, 3, 6}};

    public static final int[][] INVALID_BLOCKS = {  {1, 2, 3, 4, 5, 6, 7, 8, 9},
                                                    {2, 3, 4, 5, 6, 7, 8, 9, 1},
                                                    {3, 4, 5, 6, 7, 8, 9, 1, 2},
                                                    {4, 5, 6, 7, 8, 9, 1, 2, 3},
                                                    {5, 6, 7, 8, 9, 1, 2, 3, 4},
                                                    {6, 7, 8, 9, 1, 2, 3, 4, 5},
                                                    {7, 8, 9, 1, 2, 3, 4, 5, 6},
                                                    {8, 9, 1, 2, 3, 4, 5, 6, 7},
                                                    {9, 1, 2, 3, 4, 5, 6, 7, 8}};

    private ByteArrayOutputStream console;

    @BeforeEach
    private void setUp() {
        /* Redirect the stdout to our print stream
         * to monitor console output. */
        console = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(console);
        System.setOut(ps);
    }

    private String getStrippedConsole() {
        return console.toString().stripTrailing();
    }

    @Test
    public void EndToEndSuppliedValidTest() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(SudokuVerifierTest.VALID);
        sudokuVerifier.verifySolution();
        assertEquals("Solution is Valid", getStrippedConsole());
    }

    @Test
    public void EndToEndSuppliedInvalidTest() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(SudokuVerifierTest.INVALID);
        sudokuVerifier.verifySolution();
        assertEquals("Solution is Invalid", getStrippedConsole());
    }

    @Test
    public void InvalidRowsReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(SudokuVerifierTest.INVALID_ROWS);
        sudokuVerifier.verifySolution();
        assertEquals("Solution is Invalid", getStrippedConsole());
    }

    @Test
    public void InvalidColsReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(SudokuVerifierTest.INVALID_COLS);
        sudokuVerifier.verifySolution();
        assertEquals("Solution is Invalid", getStrippedConsole());
    }

    @Test
    public void InvalidBlocksReturnsInvalid() {
        SudokuVerifier sudokuVerifier = new SudokuVerifier(SudokuVerifierTest.INVALID_BLOCKS);
        sudokuVerifier.verifySolution();
        assertEquals("Solution is Invalid", getStrippedConsole());
    }
}