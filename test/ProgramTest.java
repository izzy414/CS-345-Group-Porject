/**
 * Authors: Isabel Zabalza, Chris Machado, Jose Chavez, Michael Sava
 * Date: 11/21/23
 * Class: CS 345
 * File: MinimumPriorityQueueTest.java
 * Purpose: Tests the program to make sure that it runs and produces an output
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.HuffmanEncoding;
import model.Node;

class ProgramTest {
	
	@Test
	void testCanRunCorrectly() {
		ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(testOut));
		try {
			String testInput = new String(Files.readAllBytes(Paths.get("test1in.txt")));
			String expectedOutput = new String(Files.readAllBytes(Paths.get("test1out.txt")));
			
			assertTrue(testInput.length() > 0);
			assertTrue(expectedOutput.length() > 0);
			
			HuffmanEncoding encoding = new HuffmanEncoding();
			String output = encoding.compressThenExpand(testInput);
			Node root = encoding.createTree(testInput);
			encoding.printTree(root);
			encoding.printTable(root);
			System.out.println(output);
			
			assertTrue(testOut.toString().length() > 0);
		} catch (IOException e) {
			System.out.println(e);
			fail(e.toString());
		}
		System.setOut(originalOut);
	}

}
