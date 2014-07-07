Name: Kai-Zhan Lee
UNI: kl2792
Assignment 6.5 README

Running the Program: Enter two integers on the command line. The first should
					 be the desired number of ants, and the second should be
					 the desired number of sugar pieces.

class Game:
	Overview:
		Game is the top class that contains the main and manipulates the ants
		and sugar pieces.
	Notes:
		- I included two try-catch clauses to make sure that the two inputs are
		  both positive integers (hopefully I did them right and will get
		  bonus).
		- I used vectors for keeping the information about sugar pieces because
		  one can easily remove items from vectors (so when I need to remove a 
		  piece of sugar from the vector, I can do so easily). I used a vector
		  to keep the information for ant as well because vectors are generally
		  easier to manage.

class Ant:
	Overview:
		Ant represents an ant that always chases after the nearest piece of
		sugar, if any. 
	Notes:
		- I have an allSugar vector that contains all of the pieces of sugar
		  (and consequently their locations) because all ants need to know
		  where all of the pieces of sugar are, and it is easiest to just pass
		  in the allSugar vector and modify that.

class Sugar:
	Overview:
		Sugar represents a piece of sugar (that an ant will eventually come
		for).
	Notes:
		- I make a boolean eaten (initially set to false because all sugar
		  pieces are initially uneaten) to let the turn loop know that, if it
		  is true, this sugar should be removed from the allSugar vector (so no
		  more ants will go after it).

class Point:
	Overview:
		Point is just a class with x and y coordinates that are doubles. It has
		a distanceTo(Point other) convenience method and overrides the
		toString() method in java.lang.Object.
