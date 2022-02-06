import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
    int[][] grid;

    public Grid(String formData){
        Pattern p = Pattern.compile("(?![R][-][C][\\d][=])(\\d?)(?=&|$)");
        Matcher m = p.matcher(formData);
        m.results()
                .map(match -> match.group())
                .forEach(System.out::println);
    }
}
