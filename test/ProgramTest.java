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
			
			HuffmanEncoding.perform(testInput);
			
			assertEquals(expectedOutput, testOut.toString());
		} catch (IOException e) {
			System.out.println(e);
			fail(e.toString());
		}
		System.setOut(originalOut);
	}

}
