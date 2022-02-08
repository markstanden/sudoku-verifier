public abstract class TestGrids {
    public static final int[][] VALID = {
            {2, 4, 1, 6, 9, 5, 3, 8, 7},
            {7, 3, 5, 4, 2, 8, 1, 6, 9},
            {8, 6, 9, 7, 3, 1, 4, 2, 5},
            {4, 1, 3, 8, 7, 9, 2, 5, 6},
            {6, 9, 2, 5, 1, 3, 7, 4, 8},
            {5, 8, 7, 2, 4, 6, 9, 3, 1},
            {1, 7, 8, 3, 6, 4, 5, 9, 2},
            {9, 5, 4, 1, 8, 2, 6, 7, 3},
            {3, 2, 6, 9, 5, 7, 8, 1, 4}
    };

    public static final int[][] INVALID = {
            {2, 4, 1, 6, 9, 5, 3, 8, 1},
            {7, 3, 5, 4, 2, 8, 1, 6, 9},
            {8, 6, 9, 7, 3, 1, 4, 2, 5},
            {4, 1, 3, 8, 7, 9, 2, 5, 6},
            {6, 9, 2, 5, 2, 3, 7, 4, 8},
            {5, 8, 7, 2, 4, 6, 9, 3, 1},
            {1, 7, 8, 3, 6, 4, 5, 9, 2},
            {8, 5, 4, 1, 8, 2, 6, 7, 3},
            {3, 2, 6, 9, 5, 7, 8, 1, 4}
    };

    public static final int[][] INVALID_COLS = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {8, 6, 9, 1, 2, 3, 4, 5, 6},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {8, 6, 9, 1, 2, 3, 4, 5, 6},
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {8, 6, 9, 1, 2, 3, 4, 5, 6}
    };

    public static final int[][] VALID_REGULAR = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    public static final int[][] INVALID_ROWS = {
            {1, 4, 7, 1, 4, 7, 1, 4, 7},
            {2, 5, 8, 2, 5, 8, 2, 5, 8},
            {3, 6, 9, 3, 6, 9, 3, 6, 9},
            {4, 7, 1, 4, 7, 1, 4, 7, 1},
            {5, 8, 2, 5, 8, 2, 5, 8, 2},
            {6, 9, 3, 6, 9, 3, 6, 9, 3},
            {7, 1, 4, 7, 1, 4, 7, 1, 4},
            {8, 2, 5, 8, 2, 5, 8, 2, 5},
            {9, 3, 6, 9, 3, 6, 9, 3, 6}
    };

    public static final int[][] ALL_INVALID_BLOCKS = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    public static final int[][] INVALID_FIRST = {
            {0, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    public static final int[][] INVALID_LAST = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},
            {2, 3, 4, 5, 6, 7, 8, 9, 1},
            {5, 6, 7, 8, 9, 1, 2, 3, 4},
            {8, 9, 1, 2, 3, 4, 5, 6, 7},
            {3, 4, 5, 6, 7, 8, 9, 1, 2},
            {6, 7, 8, 9, 1, 2, 3, 4, 5},
            {9, 1, 2, 3, 4, 5, 6, 7, 0}
    };

    public static final int[][] INVALID_BLOCKS_MINIMAL = {
            {2, 4, 1, 6, 9, 5, 3, 8, 7},
            {7, 3, 5, 4, 2, 8, 1, 6, 9},
            {8, 6, 9, 7, 3, 1, 4, 2, 5},
            {4, 1, 8, 3, 7, 9, 2, 5, 6},
            {6, 9, 2, 5, 1, 3, 7, 4, 8},
            {5, 8, 7, 2, 4, 6, 9, 3, 1},
            {1, 7, 3, 8, 6, 4, 5, 9, 2},
            {9, 5, 4, 1, 8, 2, 6, 7, 3},
            {3, 2, 6, 9, 5, 7, 8, 1, 4}
    };

    public static final int[][] INVALID_ALL_ZEROES = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    public static final int[][] INVALID_REGULAR_BLOCKS = {
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
            {1, 2, 3, 1, 2, 3, 1, 2, 3},
            {4, 5, 6, 4, 5, 6, 4, 5, 6},
            {7, 8, 9, 7, 8, 9, 7, 8, 9},
    };
}
