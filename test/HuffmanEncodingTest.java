// Authors: Elisa Zabalza, Chris Machado, Jose Chavez, Michael Sava
// Date: 11/21/23
// Class: CS 345
// File name: HuffmanEncodingTest.java
// Description: This program tests compression and decompression for the HuffmanEncoding.java file.

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.HuffmanEncoding;
import model.Node;

class HuffmanEncodingTest {

	@Test
	void testCanConvertStringsToTheirBinaryStringEquivalent() {
		String in = "";
		String in2 = "a";
		String in3 = "b";
		String in4 = "abc";
		String in5 = "A";
		String in6 = "AbC";
		String in7 = " ";
		String in8 = "A bC";
		String in9 = " cup of waTer  ";
		String in10 = "0";
		String in11 = "1";
		String in12 = "10";
		
		String expectedOut = "";
		String expectedOut2 = "01100001"; // a
		String expectedOut3 = "01100010"; // b
		String expectedOut4 = "01100001" // a
				+ "01100010" // b
				+ "01100011"; // c
		String expectedOut5 = "01000001"; // A
		String expectedOut6 = "01000001" // A
				+ "01100010" // b
				+ "01000011"; // C
		String expectedOut7 = "00100000"; // space
		String expectedOut8 = "01000001" // A
				+ "00100000" // space
				+ "01100010" // b
				+ "01000011"; // C
		String expectedOut9 = "00100000" // space
				+ "01100011" // c
				+ "01110101" // u
				+ "01110000" // p
				+ "00100000" // space
				+ "01101111" // o
				+ "01100110" // f
				+ "00100000" // space
				+ "01110111" // w
				+ "01100001" // a
				+ "01010100" // T
				+ "01100101" // e
				+ "01110010" // r
				+ "00100000" // space
				+ "00100000"; // space
		String expectedOut10 = "00110000"; // 0
		String expectedOut11 = "00110001"; // 1
		String expectedOut12 = "00110001" // 1
				+ "00110000"; // 0
		
		String actualOut = HuffmanEncoding.getBinaryString(in);
		String actualOut2 = HuffmanEncoding.getBinaryString(in2);
		String actualOut3 = HuffmanEncoding.getBinaryString(in3);
		String actualOut4 = HuffmanEncoding.getBinaryString(in4);
		String actualOut5 = HuffmanEncoding.getBinaryString(in5);
		String actualOut6 = HuffmanEncoding.getBinaryString(in6);
		String actualOut7 = HuffmanEncoding.getBinaryString(in7);
		String actualOut8 = HuffmanEncoding.getBinaryString(in8);
		String actualOut9 = HuffmanEncoding.getBinaryString(in9);
		String actualOut10 = HuffmanEncoding.getBinaryString(in10);
		String actualOut11 = HuffmanEncoding.getBinaryString(in11);
		String actualOut12 = HuffmanEncoding.getBinaryString(in12);
		
		assertEquals(expectedOut, actualOut);
		assertEquals(expectedOut2, actualOut2);
		assertEquals(expectedOut3, actualOut3);
		assertEquals(expectedOut4, actualOut4);
		assertEquals(expectedOut5, actualOut5);
		assertEquals(expectedOut6, actualOut6);
		assertEquals(expectedOut7, actualOut7);
		assertEquals(expectedOut8, actualOut8);
		assertEquals(expectedOut9, actualOut9);
		assertEquals(expectedOut10, actualOut10);
		assertEquals(expectedOut11, actualOut11);
		assertEquals(expectedOut12, actualOut12);
	}
	
	@Test
	void testCanCreateHuffmanTreesFromScannerLines() {
		ByteArrayInputStream stream = new ByteArrayInputStream((
				"a\n"
				+ "b\n"
				+ "aB\n"
				+ "A\n"
				+ "hi\n"
				+ "hI there\n").getBytes());
		try (Scanner scanner = new Scanner(stream)) {
			HuffmanEncoding encoding = new HuffmanEncoding();
			
			// "a"
			Node root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('a', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "b"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('b', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "aB"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(2, root.getFreq());
			assertEquals('\0', root.getSymbol());
			Node zero = root.getZero();
			Node one = root.getOne();
			assertNotNull(zero);
			assertEquals(1, zero.getFreq());
			// When zero and one have the same frequency, the zero
			// node should be the one with the smaller
			// lexicographic character.
			assertEquals('B', zero.getSymbol());
			assertNotNull(one);
			assertEquals(1, one.getFreq());
			assertEquals('a', one.getSymbol());
			
			// "A"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('A', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "hi"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(2, root.getFreq());
			assertEquals('\0', root.getSymbol());
			zero = root.getZero();
			one = root.getOne();
			assertNotNull(zero);
			assertEquals(1, zero.getFreq());
			assertEquals('h', zero.getSymbol());
			assertNotNull(one);
			assertEquals(1, one.getFreq());
			assertEquals('i', one.getSymbol());
			
			// "hI there"
			// tree should look like: (_ means space character)
			//        8
			//    4       4
			//  2   2   e   h
			// _ I r t        
			root = encoding.createTree(scanner.nextLine());
			assertEquals(8, root.getFreq());
			assertEquals('\0', root.getSymbol());
			
			assertEquals(4, root.getZero().getFreq());
			assertEquals('\0', root.getZero().getSymbol());
			assertEquals(2, root.getZero().getZero().getFreq());
			assertEquals('\0', root.getZero().getZero().getSymbol());
			assertEquals(1, root.getZero().getZero().getZero().getFreq());
			assertEquals(' ', root.getZero().getZero().getZero().getSymbol());
			assertEquals(1, root.getZero().getZero().getOne().getFreq());
			assertEquals('I', root.getZero().getZero().getOne().getSymbol());
			assertEquals(2, root.getZero().getOne().getFreq());
			assertEquals('\0', root.getZero().getOne().getSymbol());
			assertEquals(1, root.getZero().getOne().getZero().getFreq());
			assertEquals('r', root.getZero().getOne().getZero().getSymbol());
			assertEquals(1, root.getZero().getOne().getOne().getFreq());
			assertEquals('t', root.getZero().getOne().getOne().getSymbol());
			
			assertEquals(4, root.getOne().getFreq());
			assertEquals('\0', root.getOne().getSymbol());
			assertEquals(2, root.getOne().getZero().getFreq());
			assertEquals('e', root.getOne().getZero().getSymbol());
			assertEquals(2, root.getOne().getOne().getFreq());
			assertEquals('h', root.getOne().getOne().getSymbol());
		}
	}
	
	@Test
	void testCanCompressThenDecompressMessageFromScannerLines() {
		ByteArrayInputStream stream = new ByteArrayInputStream((
				"a\n"
				+ "b\n"
				+ "aB\n"
				+ "Ab\n"
				+ "A\n"
				+ "hi\n"
				+ "hI there\n").getBytes());
		try (Scanner scanner = new Scanner(stream)) {
			HuffmanEncoding encoding = new HuffmanEncoding();
			
			// "a"
			String message = scanner.nextLine();
			Node root = encoding.createTree(message);
			String encoded = encoding.compressMessage(root, message);
			assertEquals("1", encoded);
			String decoded = encoding.decompressMessage(root, encoded);
			assertEquals("a", decoded);
			
			// "b"
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("1", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("b", decoded);
			
			// "aB"
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("10", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("aB", decoded);
			
			// "Ab"
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("01", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("Ab", decoded);
			
			// "A"
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("1", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("A", decoded);
			
			// "hi"
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("01", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("hi", decoded);
			
			// "hI there"
			// tree should look like: (_ means space character)
			//        8
			//    4       4
			//  2   2   e   h
			// _ I r t        
			message = scanner.nextLine();
			root = encoding.createTree(message);
			encoded = encoding.compressMessage(root, message);
			assertEquals("11001000011111001010", encoded);
			decoded = encoding.decompressMessage(root, encoded);
			assertEquals("hI there", decoded);
		}
	}
}
