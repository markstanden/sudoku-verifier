package WebView;

import Sudoku.SudokuGrid;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FormProcessor
{
	public static final String WEBFORM_REGEX = "R[0-8]-C[0-8]=([0-9]?)(?=&|$)";
	public static final String NUMSTRING_REGEX = "([0-9])";
	public static final String IS_A_NUMBER_REGEX = "^([0-9]+)$";



	/**
	 * Return a new grid object from supplied form data.
	 * Assumes passed string not sanitised and will return null if string is not
	 * in expected format.
	 *
	 * @param query
	 * 		the data string received from the front end form request.
	 * 		this should progress left to right, top to bottom.
	 * 		R0-C0=0&R0-C1=0&...
	 *
	 * @return new List&lt;List&lt;Integer&gt;&gt; object
	 */
	public static List<List<Integer>> validateFormDataToList(String query)
	{
		return regexGroupToList(WEBFORM_REGEX, query);
	}


	public static String nestedListToString(List<List<Integer>> nestedList)
	{
		List<String> flatStringList = nestedList.stream()
		                                        .map(list -> list.stream()
		                                                         .map(String::valueOf)
		                                                         .collect(Collectors.joining()))
		                                        .toList();

		return String.join("", flatStringList);
	}


	public static List<List<Integer>> validateNumstringToList(String query)
	{
		Objects.requireNonNull(query, "The string supplied cannot be null");
		if(! Pattern.matches(IS_A_NUMBER_REGEX, query)) {
			throw new IllegalArgumentException("Supplied query string is not valid");
		}
		return regexGroupToList(NUMSTRING_REGEX, query);

	}


	/**
	 * The webform can have empty values,
	 * these have been matched by the regex expression,
	 * but empty values will break the grid, so replace with 0.
	 */
	public static List<List<Integer>> regexGroupToList(String regex, String query)
	{
		Pattern regexPattern = Pattern.compile(regex);
		try {
			Matcher regexMatcher = regexPattern.matcher(query);
			return chopToNestedList(SudokuGrid.GRID_LENGTH,
			                        regexMatcher.results()
			                                    .map(matchResult -> matchResult.group(1))
			                                    .map(match -> match.isBlank() ? "0" : match)
			                                    .map(Integer::valueOf)
			                                    .toList());
		}
		catch(NullPointerException e) {
			throw new IllegalArgumentException("Empty Query sent to be validated");
		}

	}


	private static List<List<Integer>> chopToNestedList(int rowLength, List<Integer> query)
	{
		Objects.requireNonNull(query);

		if(query.size() % rowLength != 0) {
			throw new IllegalArgumentException(String.format("Invalid query length, expected multiple of %d, got %d",
			                                                 rowLength,
			                                                 query.size()));
		}
		return IntStream.range(0, rowLength)
		                .mapToObj(rowStart -> query.subList(rowLength * rowStart, rowLength * (rowStart + 1)))
		                .toList();
	}


	/**
	 * Calculates the total number
	 * of items in a nested list
	 *
	 * @param nestedList
	 * 		The nested list to be sized
	 * @param <T>
	 * 		The value type stored in the list
	 *
	 * @return a long of the total number of values stored in the lists.
	 */
	public static <T> long totalValues(List<List<T>> nestedList)
	{
		return nestedList.stream()
		                 .mapToLong(Collection::size)
		                 .sum();
	}
}