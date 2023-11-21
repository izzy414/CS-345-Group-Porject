// Authors: Elisa Zabalza, Chris Machado, Jose Chavez, Michael Sava
// Date: 11/21/23
// Class: CS 345
// File name: HuffmanEncodingTest.java
// Description: This program tests compression and decompression for the HuffmanEncoding.java file.

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
	void stringLenTest1() {
		String in = "test";
		String out = "";
		//expectedOut calculated from https://huffman-coding-online.vercel.app/
		String expectedOut = "011100";
		HuffmanEncoding huffman = new HuffmanEncoding();
		Node root = huffman.createTree(in);
		String compressedMessage = huffman.compressMessage(root, in);
		System.out.println(compressedMessage);
		assertEquals(expectedOut.length(), compressedMessage.length());
		
	}
	
	@Test
	void stringLenTest2() {
		String in = "hI there!";
		String out = "";
		//expectedOut calculated from https://huffman-coding-online.vercel.app/
		String expectedOut = "1011011111110100100101000";
		HuffmanEncoding huffman = new HuffmanEncoding();
		Node root = huffman.createTree(in);
		String compressedMessage = huffman.compressMessage(root, in);
		System.out.println(compressedMessage);
		assertEquals(expectedOut.length(), compressedMessage.length());
	}
	
	@Test
	void stringLenTest3() {
		String in = "Huffman Encoding";
		String out = "";
		//expectedOut calculated from https://huffman-coding-online.vercel.app/
		String expectedOut = "1110111111101101111010010010001011001010010101000111000110";
		HuffmanEncoding huffman = new HuffmanEncoding();
		Node root = huffman.createTree(in);
		String compressedMessage = huffman.compressMessage(root, in);
		System.out.println(compressedMessage);
		assertEquals(expectedOut.length(), compressedMessage.length());
	}
	
	@Test
	void stringLenTest4() {
		String in = "Huffman Encoding is fun just kidding it sucks";
		String out = "";
		//expectedOut calculated from https://huffman-coding-online.vercel.app/
		String expectedOut = "00101111110101010001000011110011000110100010000001010101110010111110011111011010101111100110000001111111010110110000101101010101011100101111100111011011011101111010000011110";
		HuffmanEncoding huffman = new HuffmanEncoding();
		Node root = huffman.createTree(in);
		String compressedMessage = huffman.compressMessage(root, in);
		System.out.println(compressedMessage);
		assertEquals(expectedOut.length(), compressedMessage.length());
	}
	
	@Test
	void stringLenTest5() {
		String in = "rnhkjlrgnjkregnkjdfbgjerlngmgmldfkmhkleamthmn'dtnatk'hmaerk'lhmerhn";
		String out = "";
		//expectedOut calculated from https://huffman-coding-online.vercel.app/
		String expectedOut = "0001001111011001111000001011100001101100010101011100011001100101110011110001011001110100001100100101101010110101100001011100101101011110111100101011011010110101111010100111010010110101001101111010011111011111010110111010000011111011100111101010100001111100";
		HuffmanEncoding huffman = new HuffmanEncoding();
		Node root = huffman.createTree(in);
		String compressedMessage = huffman.compressMessage(root, in);
		System.out.println(compressedMessage);
		assertEquals(expectedOut.length(), compressedMessage.length());
	}
	
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
