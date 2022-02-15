package Sudoku;

import WebView.FormProcessor;

import java.util.List;
import java.util.Random;

public class StarterGrids
{
	public static final String[] STARTER_GRIDS = new String[]{
			/* 17 Clues Diagonal Symmetry*/
			"000000001" + "000000023" + "004005000" + "000100000" + "000030600" + "007000580" + "000067000" + "010004000" + "520000000",

			/* 18 clues and orthogonal symmetry */
			"000000000" + "090010030" + "006020700" + "000304000" + "210000098" + "000000000" + "002506400" + "080000010" + "000000000",

			/* An automorphic Sudoku with 24 clues and complete geometric symmetry */
			"000603000" + "030010050" + "009000200" + "700106009" + "020000080" + "100409003" + "008000100" + "050090070" + "000704000",

/*A Sudoku with 19 clues and two-way orthogonal symmetry*/
			"000902000" + "040000050" + "002000300" + "200000007" + "000456000" + "600000009" + "007000800" + "030000040" + "000207000",

			/** Development and Testing Grid */
			"085601023462308150003005068578030649620984735349567812050803206836000504290756380"};
	private static final Random rand = new Random();


	/**
	 * Randomly returns one of the starter grids.
	 */
	public static List<List<Integer>> getRandomGrid()
	{
		var randno = rand.nextInt(STARTER_GRIDS.length);
		System.out.println(randno);
		var list = FormProcessor.validateNumstringToList(STARTER_GRIDS[randno]);

		return list;
	}
}