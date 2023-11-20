# CS-345-Group-Project

Our program prompts the user for a string and then uses that string to create a tree that has a unique path for each individual 
character made up of 1’s and 0’s. Then it traverses through the tree to print out the new unique codes associated with that letter 
for each letter until the end of the string. Once the new code is displayed it uses that code along with the tree to decode 
it to the original string message and displays the tree and a table version of the tree it used to decode the string.


# Table of contents

- Requirements
- Program Configuration
- Test Configuration
- Authors


# Requirements

- JUnit Jupiter (needed for testing)


# Program Configuration

Program Configuration for the console.

1. Run javac model/*.java to compile all the java files in the model directory from the main file CS-345-Group-Project (or whatever software used to compile).
2. Run java model.HuffmanEncoding to initiate the program (or whatever software used to run).
3. Input string into the console and wait for directed output (i.e. tree, table, coded message, and decoded message).


# Test Configuration

Testing was done in Eclipse. 

1. Run HuffmanEncodingTest (tests compression and decompression).
2. Run MinimumPriorityQueueTest (tests Minimum Priority Queue).
3. Run MoreTests (tests compression and decompression).
4. Run NodeTest (tests the different node aspects).
5. Run ProgramTest (tests if the program runs).


# Authors

- Elisa Zabalza
- Chris Machado
- Jose Chavez
- Michael Sava
