Name: Kai-Zhan Lee
UNI: kl2792
Project README

Running the Program:
	- Copy and paste (or just type) the following into terminal in the folder
	  you downloaded the project to:
		unzip Project.zip
		cd Project
		javac Game.java # All other classes will be compiled with this command.
		java Game # And enjoy the game!
	- Note: You MUST run this program in terminal, command prompt, or whatever
	  program your computer uses to open shells if you want to be able to save
	  or load files.
Overview:
	This program inquires what game the user would like to play (the only
	possible one currently is chess), then plays it. For the chess game, the
	user can choose to play against another user (or himself/herself) or a
	computer or watch two computers play each other. Each computer plays using
	the minimax algorithm, looking a user-specified number of steps ahead.
Notes:
	- You may need to update to Java 8 / Java 7 because certain earlier
	  versions do not support iterator for loops with arrays.
	- I created the Game class in order to make it easy to redesign the
	  "choosing" JFrame and implement other games by adding more buttons (or
	  whatever selection method the redesigned code is using) and more
	  packages.