package model;

public class MinimumPriorityQueue<T extends Comparable<Node>> {
	// Creates the Minimum PriorityQueue.
	private Heap<T> MinQueue;
	private int size;
	
	public MinimumPriorityQueue() {
		MinQueue = new Heap<T>();
	}
	
	public void enqueue(Node node) {
		// Adds the node to the queue.
		MinQueue.add(node);
	}

	public int size() {
		// Returns the size of the queue.
		return MinQueue.size();
		//return 0;
	}

	public Node dequeue() {
		// Removes and returns the node with the minimum priority.
		return MinQueue.poll();
		//return null;
	}
}

class Heap<E extends Comparable<Node>> {
	private Node[] heap;
	private static final int INTIAL_CAPACITY = 5;
	private int size;
	public Heap() {
		heap = new Node[INTIAL_CAPACITY];
		heap[0] = null; // the element at index 0
		size = 0;
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
			// what happens if two characters have the same weight
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
	
	/** add is a method that adds a Node object to the heap
	 * @param newPatient is a Node object that will be added
	 * @return N/A
	 */
	public void add(Node newNode) {
		size++;
		if (size>= heap.length-1)
			resize(2*heap.length);
		heap[size] = newNode;
		bubbleUp(size);
	}

	/** resize is a method that resizes the heap when it gets full
	 * of Node objects, so more room can be created
	 * @param capacity is an integer that might be the 
	 * 	new capacity of the heap
	 * @return N/A
	 */
	private void resize(int capacity) 
	{
		// a temporary Node array is created to maintain
		// the current elements but a new capacity
		Node[] temp = new Node[capacity];
		size = capacity < size ? capacity:size;
		for (int i=0;i< size;i++) {
			temp[i] = heap[i];
			}
		heap = temp;
	}
	
	/** get is a method that retrieves and returns a Node object
	 * @param i is an integer that represents the location of the 
	 * 	Node object that is wanted
	 * @return a Node object
	 */
	public Node get(int i) {
		return heap[i];
	}

	/** remove is a method to remove the first patient in the queue
	 * @param N/A
	 * @return the first patient in the queue which is a Node object
	 */
	public Node poll()
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

	/** size is a method to get the size of the heap
	 * @param N/A
	 * @return an integer representation of the size of the heap
	 */
	public int size() {return size;}

	/** toString is a method to represent the heap in a String
	 * @param N/A
	 * @return a string representation of the heap
	 */
	public String toString()
	{
		String strHeap = "{";
		// i=1, to skip over the null value at heap[0]
		for (int i = 1; i <= size; i++)
			strHeap += (i == 1 ? "" : ", ") + heap[i];
		return strHeap + "}";
	}
}
