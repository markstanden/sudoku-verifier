package Sudoku;

import Grid.Grid;

import java.util.List;

/**
 * Encapsulate the storage of the grid to hide its
 * implementation from the classes using it.
 * The grid class produces sequential streams representing elements of the grid.
 *
 * @author Mark Standen
 * @version 1.0.0
 */
public class SudokuGrid extends Grid<Integer>
{
	public static final int BLOCK_SIZE = 3;
	public static final int GRID_LENGTH = 9;


	public SudokuGrid(List<List<Integer>> listOfIntegerList)
	{
		super(listOfIntegerList, SudokuGrid.BLOCK_SIZE, SudokuGrid.BLOCK_SIZE);
	}

}