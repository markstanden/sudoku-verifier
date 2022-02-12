package Resources;

import Converters.Converters;

import java.util.List;

public class TestGrids
{
	public static final List<List<Integer>> EMPTY_EMPTY = Converters.arrayTo2DList(new Integer[][]{});


	public static final List<List<Integer>> MISMATCHED_ROWS_FIRST_EMPTY = Converters.arrayTo2DList(new Integer[][]{{}, {4, 5, 6}, {7, 8, 9}});

	public static final List<List<Integer>> MISMATCHED_ROWS_LAST_EMPTY = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3}, {4, 5, 6}, {}});

	public static final List<List<Integer>> MISMATCHED_ROWS_FIRST_LARGER = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3, 4}, {5, 6, 7}, {8, 9, 10}});

	public static final List<List<Integer>> MISMATCHED_ROWS_FIRST_SMALLER = Converters.arrayTo2DList(new Integer[][]{{1, 2}, {3, 4, 5}, {6, 7, 8}});


	public static final List<List<Integer>> ONE_BY_EMPTY = Converters.arrayTo2DList(new Integer[][]{{}});

	public static final List<List<Integer>> ONE_BY_ONE = Converters.arrayTo2DList(new Integer[][]{{1}});

	public static final List<List<Integer>> ONE_BY_FOUR = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3, 4}});

	public static final List<List<Integer>> ONE_BY_NINE = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9}});


	public static final List<List<Integer>> TWO_BY_TWO = Converters.arrayTo2DList(new Integer[][]{{1, 2}, {3, 4}});

	public static final List<List<Integer>> TWO_BY_TWO_INVERSE = Converters.arrayTo2DList(new Integer[][]{{1, 3}, {2, 4}});

	public static final List<List<Integer>> TWO_BY_FOUR = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3, 4}, {5, 6, 7, 8}});


	public static final List<List<Integer>> THREE_BY_THREE = Converters.arrayTo2DList(new Integer[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

	public static final List<List<Integer>> THREE_BY_THREE_INVERSE = Converters.arrayTo2DList(new Integer[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}});


	public static final List<List<Integer>> FOUR_BY_ONE = Converters.arrayTo2DList(new Integer[][]{{1}, {2}, {3}, {4}});

	public static final List<List<Integer>> FOUR_BY_TWO = Converters.arrayTo2DList(new Integer[][]{{1, 5}, {2, 6}, {3, 7}, {4, 8}});


	public static final List<List<Integer>> EIGHT_BY_ONE = Converters.arrayTo2DList(new Integer[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}});

	public static final List<List<Integer>> NINE_BY_ONE = Converters.arrayTo2DList(new Integer[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}});

}