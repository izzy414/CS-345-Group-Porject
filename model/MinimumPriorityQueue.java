// Author: Chris Machado, Isabel Zabalza, Jose Chavez, Michael Sava
// File name: MinimumPriorityQueue.java
// Date: 11/21/2023
// Class: CSC 345
// Purpose: The MinimumPriorityQueue class is backed
//   by an array that maintains min-heap order.
package model;

public class MinimumPriorityQueue {
	private Node[] heap;
	private int size;
	
	// This constructor takes a frequencies array and the number of symbols that
	// the frequency array contains. It creates an heap array that is just large
	// enough to contain the character leaf nodes in the Huffman tree. The
	// frequencies array is traversed from the smallest asciiCode to the largest
	// to create the char nodes which are then added to the heap array in the same
	// order. This heap is put into min-heap order.
	public MinimumPriorityQueue(int[] freqs, int symbolCount) {
		// The most values we can possibly have in our heap at one time is 1+symbolCount (one char node for each unique ASCII character in the original message, plus the null value at index zero).
		// Since symbolCount will only ever be as large as 256, this is not much space being used up in the worst case and so we will hard code this heap size to 1+symbolCount and never resize
		// this heap to make it smaller as we dequeue from the minimum priority queue. This way, we don't need to implement resize or take it into account.
		heap = new Node[1+symbolCount];
		heap[0] = null; // the element at index 0
		size = 0;
		
		// Fill in each index of our heap array.
		for (char asciiCode = 0; asciiCode < 256; asciiCode++) {
			int freq = freqs[asciiCode];
			if (freq > 0) {
				// insert at 1+size to account for the null value at index zero.
				heap[1+size] = (new Node(asciiCode, freq));
				size++;
			}
		}
		
		// Put our heap array in min-heap order.
		heapify();
	}
	
	// Order the heap array so that the smallest value is at the root
	private void heapify() {
		// Get the first node that is not an external node.
		int start = parent(heap.length-1);
		
		// Bubble down nodes until we reach the head of the heap array.
		while (start > 0) {
			// Bubble down the node at index [start] so that
			// all nodes from [start] to the end of the heap array
			// are in heap order.
			bubbleDown(start);
			
			// Go to the next node that may not be in heap order.
			start--;
		}
	}
	
	/** parent is a method that gets the parent of i, if there is one
	 * @param i is an integer that gets it's parent
	 * @return An integer that allows you to get it's parent
	 */
	private int parent(int i) {return i / 2;}
	
	/** left is a method that gets the left child of i, if there is one
	 * @param i is an integer that gets it's left child
	 * @return An integer that allows you to get it's left child
	 */
	private int left(int i) {return 2 * i;}
	
	/** right is a method that gets the right child of i, if there is one
	 * @param i is an integer that gets it's right child
	 * @return An integer that allows you to get it's right child
	 */
	private int right(int i) {return 2 * i + 1;}

	
	/** compare is a method that checks if node1 has a higher priority
	 * than node2, if so, returns true; otherwise, it returns false
	 * @param node1 is the first Node object
	 * 		  node2 is the second Node object
	 * @return A boolean value depending if node1 has a higher priority
	 * 		  than node2
	 */
	private boolean compare(Node node1, Node node2) {
		// First checks if the node frequencies are equal, then checks 
		// which char alphabetically comes first
		if (node1.getFreq() == node2.getFreq()) {
			return compareChar(node1,node2);
			// what happens if two characters have the same frequency
		}
		// If priorities are not equal
		else {
			return node1.getFreq() < node2.getFreq();}
	}
	
	/** compareChar is a method to check which node char
	 * comes first alphabetically
	 * @param node1 is the first node
	 * 		  node2 is the second node
	 * @return A boolean value;
	 * 			true,  if node1 char comes first
	 * 			false, if node2 char comes first
	 */
	private boolean compareChar(Node node1, Node node2) {
		if ((node1.compareToChar(node2)) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/** bubbleUp is a method that "bubbles up" a Node object,
	 * or pushes a Node closer to the front of the queue
	 * @param i is an integer to represent the index of the Node
	 * @return N/A
	 */
	private void bubbleUp(int i)
	{
		if (i > 1){
			if ((compare(heap[i], heap[parent(i)]))) {
				Node tempHold = heap[parent(i)];
				heap[parent(i)] = heap[i];
				heap[i] = tempHold;
				bubbleUp(parent(i));
			}
		}
	}
	
	/** bubbleDown is a method that "bubbles down" a Node object,
	 * or pushes a Node closer to the back of the queue
	 * @param i is an integer to represent the index of the Node
	 * @return N/A
	 */
	private void bubbleDown(int i) {
		if (left(i) <= size) {
			int topChild = left(i);
			// Checks if the right side has a higher priority than the left side
			if (right(i) <=size && compare(heap[right(i)], heap[left(i)])) {
				topChild = right(i);
			}
			if (compare(heap[topChild], heap[i])) {
				Node temp = heap[topChild];
				heap[topChild] = heap[i];
				heap[i] = temp;
			}
			bubbleDown(topChild);
		}
	}
	
	/** enqueue is a method that adds a Node object to the heap
	 * @param newNode is a Node object that will be added
	 * @return N/A
	 */
	public void enqueue(Node newNode) {
		heap[1+size] = newNode;
		size++;
		bubbleUp(size);
	}

	/** dequeue is a method to remove the first patient in the queue
	 * @param N/A
	 * @return the first patient in the queue which is a Node object
	 */
	public Node dequeue()
	{
		if (isEmpty())
			return null;
		Node nodeOut = heap[1];
		heap[1]=heap[size];
		bubbleDown(1);
		size--;
		return nodeOut;
	}
	
	/** isEmpty is a method to check if the heap is empty
	 * @param N/A
	 * @return the truth value of if the size is equal to 0
	 * 			true if the size is 0
	 * 			false otherwise
	 */
	public boolean isEmpty() {return size == 0;}

	/** size is a method to get the size of the queue
	 * @param N/A
	 * @return an integer representation of the size of the queue
	 */
	public int size() {return size;}
}
