package model;

public class MinimumPriorityQueue<T> {
	// Creates the Minimum PriorityQueue.
	PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.priority));
	
	public void enqueue(Node node) {
		// Adds the node to the queue.
		priorityQueue.add(node);
	}

	public int size() {
		// Returns the size of the queue.
		return priorityQueue.size();
		//return 0;
	}

	public Node dequeue() {
		// removes and returns the node with the minimum priority.
		return priorityQueue.poll();
		//return null;
	}
}
