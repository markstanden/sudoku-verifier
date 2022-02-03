This is a challenging programming project in which you will practise what you have learned about two-dimensional arrays. You will write a program that checks a completed sudoku puzzle. Don’t worry if you have never done a sudoku; the rules will be explained!

A sudoku verifier can be given a 9-by-9 array of integers to check if it is a valid solution to a sudoku puzzle.

To be a valid solution a completed grid must pass these three tests:

Each row must contain each of the integers 1, 2, 3, 4, 5, 6, 7, 8, 9.
Each column must contain each of the integers 1, 2, 3, 4, 5, 6, 7, 8, 9.
Each of the blocks (the nine smaller squares) must contain each of the integers 1, 2, 3, 4, 5, 6, 7, 8, 9.
Figure 11 is an example of a correctly completed sudoku puzzle:

Described image
Figure 11 A correctly completed sudoku puzzle
Long description
a.In BlueJ, open the empty project sudoku-verifier and create a class called SudokuVerifier.
b.Add an instance variable, grid, that should be declared as a two-dimensional array of int values.
c.Copy the populateGrid1 and populateGrid2 methods from the README file and paste them into your SudokuVerifier class. You should read the method comments but do not alter these methods, which provide example sudoku grids for testing purposes. (Use Auto-layout in BlueJ to tidy up the layout.)
d.Add a constructor with one parameter of type int[][]. The constructor should initialise grid to this parameter.
e.Add a second constructor, for testing purposes, with one boolean parameter. If the parameter is true, populateGrid1 is executed (initialising grid to a correct solution); otherwise populateGrid2 is executed (initialising grid to an incorrect solution).
Note that this constructor gives you a way of creating instances of SudokuVerifier with a correct solution and an incorrect solution. You should test each of the methods in part (f) as you go along, using these instances.

f.Write the following helper methods. You may want to make them public to begin with so you can test them in the Code Pad or using the Object Bench. You may also want to create other helper methods if you feel they would be useful.
Hint: In checkARow, checkAColumn and checkABlock you may find it useful to create a local collection to keep track of which of the nine digits you have already considered. Remember, a row, column or block cannot hold duplicate digits.

i.checkARow(int)

Checks the single row with the index specified by the int parameter. If the row is correct true is returned; otherwise false is returned.

ii.checkAllRows()

Checks all of the rows by repeatedly invoking the method with the signature checkARow(int). If all the rows are correct true is returned; otherwise false is returned.

iii.checkAColumn(int)

Checks the single column with the index specified by the int parameter. If the column is correct true is returned; otherwise false is returned.

iv.checkAllColumns()

Checks all of the columns by repeatedly invoking the method with the signature checkAColumn(int). If all the columns are correct true is returned; otherwise false is returned.

v.checkABlock(int, int)

Checks a single block, where the two int parameters indicate the indexes of the top left-hand element of the 3 × 3 block to be checked. If the block is correct true is returned; otherwise false is returned.

Checking the blocks is more complicated than checking a row or a column, so don’t worry if you struggle with this part of the program; just do as much as you can and then make sure that you understand the code in the completed project.

vi.checkAllBlocks()

Checks all of the blocks by repeatedly invoking the method with the signature checkABlock(int, int) with appropriate parameters. If all the blocks are correct true is returned; otherwise false is returned.

g.Write the public method verifySolution, which uses your helper methods and prints a message to say whether the grid is a valid solution or not.