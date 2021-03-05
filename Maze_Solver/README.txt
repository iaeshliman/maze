How to run:

Run program and load in a text document containing the maze data.
Press solve to find a solution to the given maze.
Press restart to reset the maze without the solution.
Program comes initialized with a default example maze.
File select starts in the project directory.
A single example maze file is provided with the program.

File Format:

Files should match the following format
	1 0 2 4
	01000
	01111
	11001
	01011
	01100
The first line specifies the coordinates of the start and end in the following way
With the top left being the coordinates (0,0)
	1(start row/X) 0(start column/Y) 2(end row/X) 4(end column/Y)
The lines after are a visual representation of the maze with 0's representing walls
and 1's representing squares in the maze. Travel between squares can only be done
horizontally and vertically not diagonally.