package model;

public class Node implements Comparable<Node> {
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

	// Used to access this Node's frequency count in the HuffmanEncoding class.
	public int getFreq() {
		return freq;
	}

	// Used to access this Node's zero in the HuffmanEncoding class.
	public Node getParent() {
		return parent;
	}
	
	public int compareTo(Node other) {
		return freq - other.freq;
	}

	public int compareToChar(Node other) {
		return symbol - other.symbol;
	}

	// NOTE: Is this being used?
	/// Output the entire tree for debugging use.
	public String toString() {
		if (zero == null && one == null) {
			return symbol + "=" + Integer.toString(freq);
		} else if (one == null) {
			return symbol + "=" + Integer.toString(freq) + "0(" + zero.toString() + ")";
		} else if (zero == null) {
			return symbol + "=" + Integer.toString(freq) + "1(" + one.toString() + ")";
		} else {
			return symbol + "=" + Integer.toString(freq) + "0(" + zero.toString() + ")" + "1(" + one.toString() + ")";
		}
	}
}
