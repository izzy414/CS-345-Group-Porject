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
		zero.parent = this;
		one.parent = this;
	}

	public Node getZero() {
                return zero;
        }

        public Node getOne() {
                return one;
        }

        public char getSymbol() {
                return symbol;
        }
	
	public int getFreq() {
		return freq;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public int compareTo(Node other) {
		return freq - other.freq;
	}

	public int compareToChar(Node other) {
		return symbol - other.symbol;
	}
	
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
