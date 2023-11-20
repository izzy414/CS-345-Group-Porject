/**
 * File: MoreTests.java
 * Authors: Chris Machado, Jose Chavez, Isabel Zabalza, 
 */

package test;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.io.*;

import org.junit.jupiter.api.Test;

import model.HuffmanEncoding;
import model.Node;

public class MoreTests {
	@Test
	void loremIpsumTest() {
		String in = "";
		String out = "";
		String expected = "";
		try {
			Scanner input = new Scanner(new File("lorem-ipsum.txt"));
			while (input.hasNext()) {
				in = input.nextLine();
			}
			expected = new String(in);
			HuffmanEncoding huffman = new HuffmanEncoding();
			Node root = huffman.createTree(in);
			String compressedMSG = huffman.compressMessage(root, in);
			out = huffman.decompressMessage(root, compressedMSG);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		
		assertEquals(out, expected);
	}
	
	@Test
	void longStringWithSpacesTest() {
		String in = "";
		String out = "";
		String expected = "";
		try {
			Scanner input = new Scanner(new File("test2.txt"));
			while (input.hasNext()) {
				in += input.nextLine() + '\n';
			}
			expected = new String(in);
			HuffmanEncoding huffman = new HuffmanEncoding();
			Node root = huffman.createTree(in);
			String compressedMSG = huffman.compressMessage(root, in);
			out = huffman.decompressMessage(root, compressedMSG);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		assertEquals(out, expected);
	}
	
	@Test
	void beeMovieTest() {
		String in = "";
		String out = "";
		String expected = "";
		try {
			Scanner input = new Scanner(new File("bee movie script.txt"));
			while (input.hasNext()) {
				in += input.nextLine() + '\n';
			}
			expected = new String(in);
			HuffmanEncoding huffman = new HuffmanEncoding();
			Node root = huffman.createTree(in);
			String compressedMSG = huffman.compressMessage(root, in);
			out = huffman.decompressMessage(root, compressedMSG);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		assertEquals(out, expected);
	}
}
