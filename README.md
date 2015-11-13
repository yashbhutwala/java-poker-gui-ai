# JavaPokerGUIandAI

--------------
Big Java Poker
--------------
This program is a graphics oriented Texas Hold'em Poker varient.  The game
utilizes simple Poker AI that use simply actuary skills to determine the best
time to perform specific betting options.  The graphical layout of the game is
geared toward a multipanled screen with a "flat" 3D perspecitve in the main
panel.

-------
Authors
-------
* Morgan Eckenroth
* Devon Wasson
* Michael Hammer
* Yash Bhutwala

---------
Libraries
---------
* Slick2D - primary graphical library
* LWJGL - Used to create openGL contexts to allow for screen rendering

-----------
Compilation
-----------
1.) First build the project like usual in your IDE
2.) Now, go to the main project folder and find the jarsplice program
3.) Run this using the command "java -jar "jarsplice-0.40"
4.) Click on "Add Jars" on the side and again at the bottom.
5.) Navigate to the output folder from the IDE's build and add the main jar
file for the program, and also all of the contents of the "lib" folder.
6.) Click on "Add Natives" on the side and again at the bottom.
7.) Navigate to "Externals/lwjgl 2.9.3/natives/linux" and add all six of the
files
8.) Now click on "Main Class" and enter "view/Game" in the field
9.) Finally click "Create Fat Jar" and place the file wherever.
Run the output jar as normal.

-------
Running
-------
To run the newly compiled Fat Jar, navigate to the containing directory and
enter the command "java -jar "nameOfJar.jar"" without the outer quotes and the
game should start running.

