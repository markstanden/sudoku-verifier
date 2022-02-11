package Sudoku;

import Grid.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuVerifier
{
	public static final Set<Integer> VALID_NUMBERS = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}).collect(
			Collectors.toUnmodifiableSet());

	private final Grid<Integer> grid;


	/**
	 * Private constructor to force use of
	 * verify static method to create object.
	 *
	 * @param solution
	 * 		The solution to verify
	 */
	private SudokuVerifier(final Grid<Integer> solution)
	{
		this.grid = solution;
	}


	/**
	 * Static builder to reduce boilerplate when verifying a grid
	 *
	 * @param grid
	 * 		The sudoku grid to be verified
	 *
	 * @return true if the grid is valid, false if invalid
	 */
	public static boolean verify(Grid<Integer> grid)
	{
		SudokuVerifier verifier = new SudokuVerifier(grid);
		return verifier.isValid();
	}

	/**
	 * Static builder to reduce boilerplate when verifying a grid
	 *
	 * @param grid
	 * 		The sudoku grid to be verified in the form of nested List&lt;Integer&gt;
	 *
	 * @return true if the grid is valid, false if invalid
	 */
	public static boolean verify(List<List<Integer>> grid)
	{
		return verify(new SudokuGrid(grid));
	}



	/**
	 * Checks whether the grid is valid.
	 * Collects and parallel checks all rows, columns and blocks.
	 *
	 * @return returns true if the supplied grid is valid, false if invalid
	 */
	private boolean isValid()
	{
		return Stream.of(grid.rowStream(), grid.colStream(), grid.blockStream()).flatMap(x -> x).parallel().allMatch(
				this::checkAGroup);
	}


	/**
	 * Checks individual group stream (row, column, block) for validity.
	 *
	 * @param group
	 * 		the Stream of boxed Integer values of the row to be tested
	 *
	 * @return true if valid, false if invalid.
	 */
	private boolean checkAGroup(final Stream<Integer> group)
	{
		return group.parallel().collect(Collectors.toSet()).containsAll(VALID_NUMBERS);
	}

}