import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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

    @Test
    void toRowStream() {
        Stream<IntStream> testStream = Arrays.stream(new IntStream[]{
                //public static final int[][] INVALID_COLS = {
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6})
        });
        Grid testGrid = new Grid(TestGrids.INVALID_COLS);
        assertArrayEquals(testStream.flatMapToInt(x -> x).toArray(), testGrid.rowStream().flatMapToInt(x -> x).toArray());
    }

    @Test
    void toColStream() {
        Stream<IntStream> testStream = Arrays.stream(new IntStream[]{
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6})
        });
        Grid testGrid = new Grid(TestGrids.INVALID_ROWS);
        assertArrayEquals(testStream.flatMapToInt(x -> x).toArray(), testGrid.colStream().flatMapToInt(x -> x).toArray());
    }

    @Test
    void rowAsStream() {
        IntStream[] testStream = new IntStream[]{
                //public static final int[][] INVALID_COLS = {
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{8, 6, 9, 1, 2, 3, 4, 5, 6})
        };
        Grid testGrid = new Grid(TestGrids.INVALID_COLS);
        IntStream.range(0, 9)
                .forEach(row -> assertArrayEquals(testStream[row].toArray(),
                        testGrid.getRowAsStream(row).toArray(),
                        "Row Number: ".concat(String.valueOf(row)))
                );
    }

    @Test
    void colAsStream() {
        IntStream[] testStream = new IntStream[]{
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6}),
                Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}),
                Arrays.stream(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}),
                Arrays.stream(new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6})
        };
        Grid testGrid = new Grid(TestGrids.INVALID_ROWS);
        IntStream.range(0, 9)
                .forEach(col -> assertArrayEquals(testStream[col].toArray(),
                        testGrid.getColAsStream(col).toArray(),
                        "Column Number: ".concat(String.valueOf(col)))
                );
    }
}