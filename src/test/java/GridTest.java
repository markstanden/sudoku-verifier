import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class GridTest {

    @Test
    void fromRealFormDataVALID_REGULAR() {
        Grid testGrid = Grid.fromFormData(SampleFormData.VALID_REGULAR);
        assertArrayEquals(TestGrids.VALID_REGULAR, testGrid.as2DArray());
    }

    @Test
    void fromMockFormDataVALID_REGULAR() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.VALID_REGULAR));
        assertArrayEquals(TestGrids.VALID_REGULAR, testGrid.as2DArray());
    }

    @Test
    void fromMockFormDataVALID() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.VALID));
        assertArrayEquals(TestGrids.VALID, testGrid.as2DArray());
    }

    @Test
    void fromMockFormDataALL_EMPTY() {
        Grid testGrid = Grid.fromFormData(SampleFormData.ALL_EMPTY);
        assertArrayEquals(TestGrids.INVALID_ALL_ZEROES, testGrid.as2DArray());
    }

    @Test
    void fromMockFormDataALL_ZEROES() {
        Grid testGrid = Grid.fromFormData(SampleFormData.createTestString(TestGrids.INVALID_ALL_ZEROES));
        assertArrayEquals(TestGrids.INVALID_ALL_ZEROES, testGrid.as2DArray());
    }


    @Test
    void validTo2DArray() {
        Grid testGrid = new Grid(TestGrids.VALID_REGULAR);
        assertArrayEquals(TestGrids.VALID_REGULAR, testGrid.as2DArray());
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
    void toBlockStream() {
        Stream<IntStream> testStream = Arrays.stream(new IntStream[]{
                Arrays.stream(new int[]{2, 4, 1, 7, 3, 5, 8, 6, 9}),
                Arrays.stream(new int[]{6, 9, 5, 4, 2, 8, 7, 3, 1}),
                Arrays.stream(new int[]{3, 8, 7, 1, 6, 9, 4, 2, 5}),
                Arrays.stream(new int[]{4, 1, 8, 6, 9, 2, 5, 8, 7}),
                Arrays.stream(new int[]{3, 7, 9, 5, 1, 3, 2, 4, 6}),
                Arrays.stream(new int[]{2, 5, 6, 7, 4, 8, 9, 3, 1}),
                Arrays.stream(new int[]{1, 7, 3, 9, 5, 4, 3, 2, 6}),
                Arrays.stream(new int[]{8, 6, 4, 1, 8, 2, 9, 5, 7}),
                Arrays.stream(new int[]{5, 9, 2, 6, 7, 3, 8, 1, 4}),
        });
        Grid testGrid = new Grid(TestGrids.INVALID_BLOCKS_MINIMAL);
        assertArrayEquals(testStream.flatMapToInt(x -> x).toArray(), testGrid.blockStream().flatMapToInt(x -> x).toArray());
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

    @Test
    void blockAsStream() {
        Grid testGrid = new Grid(TestGrids.INVALID_BLOCKS_MINIMAL);
        assertArrayEquals(new int[]{2, 4, 1, 7, 3, 5, 8, 6, 9}, testGrid.getBlockAsStream(0, 0).toArray());
        assertArrayEquals(new int[]{6, 9, 5, 4, 2, 8, 7, 3, 1}, testGrid.getBlockAsStream(0, 3).toArray());
        assertArrayEquals(new int[]{3, 8, 7, 1, 6, 9, 4, 2, 5}, testGrid.getBlockAsStream(0, 6).toArray());
        assertArrayEquals(new int[]{4, 1, 8, 6, 9, 2, 5, 8, 7}, testGrid.getBlockAsStream(3, 0).toArray());
        assertArrayEquals(new int[]{3, 7, 9, 5, 1, 3, 2, 4, 6}, testGrid.getBlockAsStream(3, 3).toArray());
        assertArrayEquals(new int[]{2, 5, 6, 7, 4, 8, 9, 3, 1}, testGrid.getBlockAsStream(3, 6).toArray());
        assertArrayEquals(new int[]{1, 7, 3, 9, 5, 4, 3, 2, 6}, testGrid.getBlockAsStream(6, 0).toArray());
        assertArrayEquals(new int[]{8, 6, 4, 1, 8, 2, 9, 5, 7}, testGrid.getBlockAsStream(6, 3).toArray());
        assertArrayEquals(new int[]{5, 9, 2, 6, 7, 3, 8, 1, 4}, testGrid.getBlockAsStream(6, 6).toArray());
    }
}