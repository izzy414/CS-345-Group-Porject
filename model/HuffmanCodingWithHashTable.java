package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class HuffmanCodingWithHashTable {
	public void compress(InputStream inStream, OutputStream outStream) {
		InputBitstream in = new InputBitstream(inStream);
		OutputBitstream out = new OutputBitstream(outStream);
		String message = readMessage(in);
		HashTable<Character, HuffmanCode> codes = createPrefixFreeCode(message);
		writePrefixFreeCodeAndMessage(codes, message, out);
	}
	
	/// O(N)
	public void expand(InputStream inStream, OutputStream outStream) {
		InputBitstream in = new InputBitstream(inStream);
		HashTable<Character, HuffmanCode> codes = readPrefixFreeCode(in);
		expandCompressedMessageUsingCodes(in, codes);
	}
	
	// read prefix-free code from file.
	private HashTable<Character, HuffmanCode> readPrefixFreeCode(InputBitstream in) {
		File file = new File("codes.txt");
		try (Scanner scanner = new Scanner(file)) {
			//scanner.nextC
		} catch (FileNotFoundException e) {}
		return null;
	}
	
	private int readMessageLength(InputBitstream in) {
		return in.readInt();
	}
	
	// compress message and write it along with prefix-free code to file.
	private void writePrefixFreeCodeAndMessage(HashTable<Character, HuffmanCode> codes, String message, OutputBitstream out) {
		// Idea for approach came from: https://stackoverflow.com/a/759738
		// TODO: May have to call out.flush() after writing everything: https://stackoverflow.com/a/21666005
	}
	
	// read message
	private String readMessage(InputBitstream in) {
		return null;
	}
	
	// build best prefix-free code from message.
	private HashTable<Character, HuffmanCode> createPrefixFreeCode(String message) {
		// TODO: We have to build our own HashTable class as well as the priority queue: https://piazza.com/class/ll46ptlxkia7ah/post/251
		HashTable<Character, Integer> freqs = countCharacterFrequencies(message);
		MinimumPriorityQueue<Node> chars = createCharNodes(freqs);
		Node tree = combineCharNodesAndBuildTree(chars);
		HashTable<Character, HuffmanCode> codes = readCodesFromTree(tree);
		return codes;
	}
	
	private HashTable<Character, HuffmanCode> readCodesFromTree(Node tree) {
		// TODO Auto-generated method stub
		return null;
	}

	private Node combineCharNodesAndBuildTree(MinimumPriorityQueue<Node> chars) {
		// TODO Auto-generated method stub
		return null;
	}

	private MinimumPriorityQueue<Node> createCharNodes(HashTable<Character, Integer> freqs) {
		MinimumPriorityQueue<Node> queue = new MinimumPriorityQueue<Node>();
		for (char symbol : )
	}

	private HashTable<Character, Integer> countCharacterFrequencies(String message) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// expand compressed message using codes.
	private void expandCompressedMessageUsingCodes(InputBitstream in, HashTable<Character, HuffmanCode> codes) {
		int messageLength = readMessageLength(in);
		
		for (int i = 0; i < messageLength; i++) {
			
		}
	}
}
