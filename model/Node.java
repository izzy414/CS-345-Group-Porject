package model;

public class Node implements Comparable<Node> {
	private Node zero;
	private Node one;
	private char symbol;
	private int weight;
	
	/// Used to create char nodes
	public Node(char symbol, int weight) {
		this.symbol = symbol;
		this.weight = weight;
	}
	
	// Used to create internal nodes
	public Node(int weight, Node zero, Node one) {
		this.weight = weight;
		this.zero = zero;
		this.one = one;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int compareTo(Node other) {
		return weight - other.weight;
	}
	
	/// Output the entire tree for debugging use.
	public String toString() {
		return symbol + Integer.toString(weight) + "0(" + zero.toString() + ")" + "1(" + one.toString() + ")";
	}
}
