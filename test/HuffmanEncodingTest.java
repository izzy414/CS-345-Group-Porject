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
		String expectedOut2 = "1100001"; // a
		String expectedOut3 = "1100010"; // b
		String expectedOut4 = "1100001" // a
				+ "1100010" // b
				+ "1100011"; // c
		String expectedOut5 = "1000001"; // A
		String expectedOut6 = "1000001" // A
				+ "1100010" // b
				+ "1000011"; // C
		String expectedOut7 = "100000"; // space
		String expectedOut8 = "1000001" // A
				+ "100000" // space
				+ "1100010" // b
				+ "1000011"; // C
		String expectedOut9 = "100000" // space
				+ "1100011" // c
				+ "1110101" // u
				+ "1110000" // p
				+ "100000" // space
				+ "1101111" // o
				+ "1100110" // f
				+ "100000" // space
				+ "1110111" // w
				+ "1100001" // a
				+ "1010100" // T
				+ "1100101" // e
				+ "1110010" // r
				+ "100000" // space
				+ "100000"; // space
		String expectedOut10 = "110000"; // 0
		String expectedOut11 = "110001"; // 1
		String expectedOut12 = "110001" // 1
				+ "110000"; // 0
		
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
				+ "A\n"
				+ "hi\n"
				+ "hI there\n").getBytes());
		try (Scanner scanner = new Scanner(stream)) {
			HuffmanEncoding encoding = new HuffmanEncoding();
			
			// "a\n"
			Node root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('a', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "b\n"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('b', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "A\n"
			root = encoding.createTree(scanner.nextLine());
			assertEquals(1, root.getFreq());
			assertEquals('A', root.getSymbol());
			assertNull(root.getZero());
			assertNull(root.getOne());
			
			// "hi\n"
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
			assertEquals('h', zero.getSymbol());
			assertEquals(1, one.getFreq());
			assertEquals('i', one.getSymbol());
			
			// "hI there\n"
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
	void testCanCompressMessageFromScannerLines() {
		ByteArrayInputStream stream = new ByteArrayInputStream((
				"a\n"
				+ "b\n"
				+ "A\n"
				+ "hi\n"
				+ "hI there\n").getBytes());
		try (Scanner scanner = new Scanner(stream)) {
			HuffmanEncoding encoding = new HuffmanEncoding();
			
			String message = scanner.nextLine();
			Node root = encoding.createTree(message);
			//encoding.compressMessage(root, message);
			// TODO: Do asserts here
		}
	}
}
