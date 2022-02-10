package Resources;

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


}