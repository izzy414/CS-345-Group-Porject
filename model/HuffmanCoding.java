package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HuffmanCoding {
	public void compress(InputStream inStream, OutputStream outStream) {
		OutputBitstream out = new OutputBitstream(outStream);
		String noncompressedMessage = readMessage(inStream);
		Node root = createTree(noncompressedMessage);
		writeTreeAndMessage(root, noncompressedMessage, out);
	}
	
	/// O(N)
	public void expand(InputStream inStream, OutputStream outStream) {
		InputBitstream in = new InputBitstream(inStream);
		Node root = readTree(in);
		expandCompressedMessageUsingTree(in, root);
	}
	
	// read huffman tree from file.
	private Node readTree(InputBitstream in) {
		return null;
	}
	
	// compress message and write it along with huffman tree to file.
	private void writeTreeAndMessage(Node root, String message, OutputBitstream out) {
		// Idea for approach came from: https://stackoverflow.com/a/759738
		// TODO: May have to call out.flush() after writing everything: https://stackoverflow.com/a/21666005
	}
	
	// read message
	private String readMessage(InputStream in) {
		// Note: bytes in Java are smaller data types than chars, so we are
		// safe treating bytes as characters.
		byte[] messageChars = null;
		try {
			messageChars = in.readAllBytes();
		} catch (IOException e) {}
		String message = new String(messageChars);
		return message;
	}
	
	// build optimal prefix-free code from message and make a huffman tree.
	private Node createTree(String message) {
		// TODO: We have to build our own priority queue class: https://piazza.com/class/ll46ptlxkia7ah/post/251
		// We are skipping using a HashTable, like was done here: https://aquarchitect.github.io/swift-algorithm-club/Huffman%20Coding/
		// Except instead of storing the character frequencies as nodes in an array (like that article did), we will just store them as integers in an array
		// and each index of the 256-index array corresponds to a character code in ASCII.
		int[] freqs = countCharacterFrequencies(message);
		MinimumPriorityQueue<Node> chars = createSortedCharNodes(freqs);
		Node root = combineCharNodesAndBuildTree(chars);
		return root;
	}

	private Node combineCharNodesAndBuildTree(MinimumPriorityQueue<Node> nodes) {
		while (nodes.size() > 1) {
			// Join the next two nodes with the lowest frequency.
			Node child1 = nodes.dequeue();
			Node child2 = nodes.dequeue();
			Node parent = new Node(child1.getWeight() + child2.getWeight(), child1, child2);
			nodes.enqueue(parent);
		}
		
		// We have only one node left in our queue.
		// This must be the root node of our Huffman tree.
		Node root = nodes.dequeue();
		return root;
	}

	private MinimumPriorityQueue<Node> createSortedCharNodes(int[] freqs) {
		MinimumPriorityQueue<Node> queue = new MinimumPriorityQueue<Node>();
		for (char asciiCode = 0; asciiCode < 256; asciiCode++) {
			int freq = freqs[asciiCode];
			if (freq > 0) {
				queue.enqueue(new Node(asciiCode, freq));
			}
		}
		return queue;
	}

	private int[] countCharacterFrequencies(String message) {
		// Note: Default value is 0 in each index of an integer array in Java. 
		int[] freqs = new int[256];
		for (int i = 0; i < message.length(); i++) {
			char asciiCode = message.charAt(i);
			freqs[asciiCode]++;
		}
		return freqs;
	}
	
	// expand compressed message using codes.
	private void expandCompressedMessageUsingTree(InputBitstream in, Node root) {
		
	}
}
