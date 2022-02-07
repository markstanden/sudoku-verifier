import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
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
    public static int[][] fromFormData(String formData) {
        Pattern p = Pattern.compile("(?![R][-][C][\\d][=])(\\d?)(?=&|$)");
        Matcher m = p.matcher(formData);
        List<Integer> asList = m.results()
                .limit(81)
                .sequential()
                .map(MatchResult::group)
                .map(Integer::getInteger)
                .toList();
        System.out.println(asList);

        Arrays.stream(formData.split("&"))
                .sequential()
                //.filter(value -> value.matches(p.pattern()))
                .forEach(System.out::println);

        return (int[][]) IntStream.range(0, 9)
                .mapToObj(row -> asList.subList(0 * row, 9 * row))
                .map(list -> list.toArray())
                .toArray();

    }

    public int[][] to2DArray() {
        return grid;
    }
}
