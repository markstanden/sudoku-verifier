package Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Converters
{
	/**
	 * Converts a 2D array into a 2D List
	 */
	public static <T> List<List<T>> arrayTo2DList(T[][] gridToConvert)
	{
		return Arrays.stream(gridToConvert)
				.map(rowArray -> Arrays.stream(rowArray).toList())
				.toList();
	}


	public static <T> List<List<T>> nestedStreamTo2DList(Stream<Stream<T>> stream)
	{
		return stream.map(Stream::toList).toList();
	}

	public static String mockFormDataFromTestGrid(List<List<Integer>> testGrid) {
		List<String> result = new ArrayList<>();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				result.add(String.format("R%d-C%d=%d", row, col, testGrid.get(row).get(col)));
			}
		}
		return String.join("&", result);
	}
}