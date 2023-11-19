package model;

// Author: ____
public class Node {
	private Node zero;
	private Node one;
	private Node parent;
	private char symbol;
	private int freq;

	/// Used to create char nodes
	public Node(char symbol, int freq) {
		this.symbol = symbol;
		this.freq = freq;
	}

	// Used to create internal nodes
	public Node(int freq, Node zero, Node one) {
		this.freq = freq;
		this.zero = zero;
		this.one = one;
		this.symbol = 0;
		zero.parent = this;
		one.parent = this;
	}

	// Used to access this Node's zero in the HuffmanEncoding class.
	public Node getZero() {
		return zero;
	}

	// Used to access this Node's one in the HuffmanEncoding class.
	public Node getOne() {
		return one;
	}

	// Used to access this Node's symbol in the HuffmanEncoding class.
	public char getSymbol() {
		return symbol;
	}

	// Used to access this Node's frequency count in the MinimumPriorityQueue class
	// and the HuffmanEncoding Class.
	public int getFreq() {
		return freq;
	}
	
	// Used to access this Node's zero in the HuffmanEncoding class.
	public Node getParent() {
		return parent;
	}

	// Used to access and compare this Node's character to another in the MinimumPriorityQueue class.
	public int compareToChar(Node other) {
		return symbol - other.symbol;
	}
}
