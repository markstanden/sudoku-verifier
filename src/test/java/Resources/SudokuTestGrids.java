package Resources;

import Converters.Converters;

import java.util.List;

public abstract class SudokuTestGrids
{
	public static final List<List<Integer>> VALID_REAL = Converters.arrayTo2DList(
			new Integer[][]{
					{2, 4, 1, 6, 9, 5, 3, 8, 7},
					{7, 3, 5, 4, 2, 8, 1, 6, 9},
					{8, 6, 9, 7, 3, 1, 4, 2, 5},
					{4, 1, 3, 8, 7, 9, 2, 5, 6},
					{6, 9, 2, 5, 1, 3, 7, 4, 8},
					{5, 8, 7, 2, 4, 6, 9, 3, 1},
					{1, 7, 8, 3, 6, 4, 5, 9, 2},
					{9, 5, 4, 1, 8, 2, 6, 7, 3},
					{3, 2, 6, 9, 5, 7, 8, 1, 4}
			});

	public static final List<List<Integer>> INVALID = Converters.arrayTo2DList(
			new Integer[][]{
					{2, 4, 1, 6, 9, 5, 3, 8, 1},
					{7, 3, 5, 4, 2, 8, 1, 6, 9},
					{8, 6, 9, 7, 3, 1, 4, 2, 5},
					{4, 1, 3, 8, 7, 9, 2, 5, 6},
					{6, 9, 2, 5, 2, 3, 7, 4, 8},
					{5, 8, 7, 2, 4, 6, 9, 3, 1},
					{1, 7, 8, 3, 6, 4, 5, 9, 2},
					{8, 5, 4, 1, 8, 2, 6, 7, 3},
					{3, 2, 6, 9, 5, 7, 8, 1, 4}
			});

	public static final List<List<Integer>> INVALID_COLS = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6}
			});

	public static final List<List<Integer>> VALID_REGULAR = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{2, 3, 4, 5, 6, 7, 8, 9, 1},
					{5, 6, 7, 8, 9, 1, 2, 3, 4},
					{8, 9, 1, 2, 3, 4, 5, 6, 7},
					{3, 4, 5, 6, 7, 8, 9, 1, 2},
					{6, 7, 8, 9, 1, 2, 3, 4, 5},
					{9, 1, 2, 3, 4, 5, 6, 7, 8}
			});

	public static final List<List<Integer>> INVALID_ROWS = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 4, 7, 1, 4, 7, 1, 4, 7},
					{2, 5, 8, 2, 5, 8, 2, 5, 8},
					{3, 6, 9, 3, 6, 9, 3, 6, 9},
					{4, 7, 1, 4, 7, 1, 4, 7, 1},
					{5, 8, 2, 5, 8, 2, 5, 8, 2},
					{6, 9, 3, 6, 9, 3, 6, 9, 3},
					{7, 1, 4, 7, 1, 4, 7, 1, 4},
					{8, 2, 5, 8, 2, 5, 8, 2, 5},
					{9, 3, 6, 9, 3, 6, 9, 3, 6}
			});

	public static final List<List<Integer>> ALL_INVALID_BLOCKS = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{2, 3, 4, 5, 6, 7, 8, 9, 1},
					{3, 4, 5, 6, 7, 8, 9, 1, 2},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{5, 6, 7, 8, 9, 1, 2, 3, 4},
					{6, 7, 8, 9, 1, 2, 3, 4, 5},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{8, 9, 1, 2, 3, 4, 5, 6, 7},
					{9, 1, 2, 3, 4, 5, 6, 7, 8}
			});

	public static final List<List<Integer>> INVALID_FIRST = Converters.arrayTo2DList(
			new Integer[][]{
					{0, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{2, 3, 4, 5, 6, 7, 8, 9, 1},
					{5, 6, 7, 8, 9, 1, 2, 3, 4},
					{8, 9, 1, 2, 3, 4, 5, 6, 7},
					{3, 4, 5, 6, 7, 8, 9, 1, 2},
					{6, 7, 8, 9, 1, 2, 3, 4, 5},
					{9, 1, 2, 3, 4, 5, 6, 7, 8}
			});

	public static final List<List<Integer>> INVALID_LAST = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 2, 3, 4, 5, 6, 7, 8, 9},
					{4, 5, 6, 7, 8, 9, 1, 2, 3},
					{7, 8, 9, 1, 2, 3, 4, 5, 6},
					{2, 3, 4, 5, 6, 7, 8, 9, 1},
					{5, 6, 7, 8, 9, 1, 2, 3, 4},
					{8, 9, 1, 2, 3, 4, 5, 6, 7},
					{3, 4, 5, 6, 7, 8, 9, 1, 2},
					{6, 7, 8, 9, 1, 2, 3, 4, 5},
					{9, 1, 2, 3, 4, 5, 6, 7, 0}
			});

	public static final List<List<Integer>> INVALID_BLOCKS_MINIMAL = Converters.arrayTo2DList(
			new Integer[][]{
					{2, 4, 1, 6, 9, 5, 3, 8, 7},
					{7, 3, 5, 4, 2, 8, 1, 6, 9},
					{8, 6, 9, 7, 3, 1, 4, 2, 5},
					{4, 1, 8, 3, 7, 9, 2, 5, 6},
					{6, 9, 2, 5, 1, 3, 7, 4, 8},
					{5, 8, 7, 2, 4, 6, 9, 3, 1},
					{1, 7, 3, 8, 6, 4, 5, 9, 2},
					{9, 5, 4, 1, 8, 2, 6, 7, 3},
					{3, 2, 6, 9, 5, 7, 8, 1, 4}
			});

	public static final List<List<Integer>> ALL_ZEROES = Converters.arrayTo2DList(
			new Integer[][]{
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					});

	public static final List<List<Integer>> INVALID_REGULAR_BLOCKS = Converters.arrayTo2DList(
			new Integer[][]{
					{1, 2, 3, 1, 2, 3, 1, 2, 3},
					{4, 5, 6, 4, 5, 6, 4, 5, 6},
					{7, 8, 9, 7, 8, 9, 7, 8, 9},
					{1, 2, 3, 1, 2, 3, 1, 2, 3},
					{4, 5, 6, 4, 5, 6, 4, 5, 6},
					{7, 8, 9, 7, 8, 9, 7, 8, 9},
					{1, 2, 3, 1, 2, 3, 1, 2, 3},
					{4, 5, 6, 4, 5, 6, 4, 5, 6},
					{7, 8, 9, 7, 8, 9, 7, 8, 9},
					});
}