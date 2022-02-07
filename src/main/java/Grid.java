import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Encapsulate the storage of the grid to hide its
 * implementation from the classes using it.
 */
public class Grid {
    int[][] grid;

    public Grid(int[][] arrayData) {
        this.grid = arrayData;
    }

    /**
     * Return a new grid object from supplied form data.
     * Assumes passed string not sanitised and will return null if string is not
     * in expected format.
     *
     * @param formData
     * @return new Grid object
     */
    public static Grid fromFormData(String formData) {
        Pattern p = Pattern.compile("^R[0-8]-C[0-8]=([0-9]?)$");

        String[] unsafeString = formData.split("&");
        int[] safe = Arrays.stream(unsafeString)
                .filter(cellValue -> cellValue.matches(p.pattern()))
                .map(value -> {
                    Matcher m = p.matcher(value);
                    return m.results()
                            .map(matchResult -> matchResult.group(1))
                            .map(match -> match.isBlank() ? "0" : match)
                            .findFirst()
                            .orElse("0");
                })
                .mapToInt(Integer::valueOf)
                .toArray();


        int[][] out = IntStream.range(0, 9)
                .mapToObj(row ->
                    Arrays.copyOfRange(safe, 9 * row, 9 * (row + 1))
                )
                .toArray(int[][]::new);

        return new Grid(out);
    }

    public int[][] to2DArray() {
        return grid;
    }
}
