package model;

import java.util.Scanner;

public class HuffmanEncoding {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String message = scanner.nextLine();
				HuffmanEncoding encoding = new HuffmanEncoding();
				String output = encoding.compressThenExpand(message);
				Node root = encoding.createTree(message);
				encoding.printTree(root);
				encoding.printTable(root);
				System.out.println(output);
			}
		}
	}
	
	public String compressThenExpand(String message) {
		Node root = createTree(message);
		String compressedMessage = compressMessage(root, message);
		String decompressMessage = decompressMessage(root, compressedMessage);
		return compressedMessage + "\n" + decompressMessage;
	}
	
	// compress message.
	private String compressMessage(Node root, String message) {
		Node curNode = root;
		StringBuilder encodedMessage = new StringBuilder();
		for (char bit : message.toCharArray()) {
			encodedMessage = dfs(curNode, encodedMessage, bit, 0);
		}
		return encodedMessage.toString();
	}

	private StringBuilder dfs(Node root, StringBuilder encodedMessage, char bit, int level) {
		Node curNode = root;
		if (curNode.getSymbol() != 0) {
			if (curNode.getSymbol() == bit) {
				addDummyValues(encodedMessage, level);
				return encodedMessage;
			}
		}
		
		if (curNode.getSymbol() == 0 && curNode.getZero() != null) {
			level++;
			encodedMessage.append('0');
			encodedMessage = dfs(curNode.getZero(), encodedMessage, bit, level);
			encodedMessage.deleteCharAt(encodedMessage.length() - 1);
			level--;
		}
		if (curNode.getSymbol() == 0 && curNode.getOne() != null) {
			level++;
			encodedMessage.append('1');
			encodedMessage = dfs(curNode.getOne(), encodedMessage, bit, level);
			encodedMessage.deleteCharAt(encodedMessage.length() - 1);
			level--;
		}
		return encodedMessage;
	}
	
	//Decode message using codes.
	private String decompressMessage(Node root, String message) {
		StringBuilder decodedMessage = new StringBuilder();
                Node curNode = root;

                for (char bit : message.toCharArray()) {
                        if (bit == '0') {
                                curNode = curNode.getZero();
                        } else if (bit == '1') {
                                curNode = curNode.getOne();
                        }
                        if (curNode.getZero() == null && curNode.getOne() == null) {
                                decodedMessage.append(curNode.getSymbol());
                                curNode = root;
                        }
                }
                return decodedMessage.toString();
	}

	/**
	 * Adds dummy values at the end of the encoded message to be removed by dfs
	 * once the correct character is found
	 */
	private void addDummyValues(StringBuilder encodedMessage, int level) {
		int i;
		for (i = 0; i < level; i++) {
			encodedMessage.append('$');
		}
	}
	public void printTable(Node root) {
		System.out.println("**** TABLE *****");
		System.out.println("Char │ Code");
		System.out.println("─────┼──────────");
		preOrder(root, "");
	}
	
	private void preOrder(Node root, String code) {
		if (root ==null) {
			return;
		}
		if (root.getOne()!= null) {
			preOrder(root.getOne(), code+"1");
		}
		
		if (root.getZero()!= null) {
			preOrder(root.getZero(), code+"0");
		}
		
		if (root.getOne()== null && root.getZero()== null) {
			System.out.println("\""+root.getSymbol()+"\"  │ "+code);
			return;
		}
		
	}

	public void printTree(Node root) {
		String layer = "";
		if (root == null) {
			return;
		}
		if (root.getOne() != null) {
			printDiagram(root,true, "", layer,0);
		}
		
		else if (root.getZero() != null) {
			printDiagram(root,false, "", layer,0);
		}
	}

	
	/** nodeStr - Returns a string value of either the frequency or a character
	 * @param root - Node object that is either an internal or external node
	 * @return	A string of the node's value
	 */
	private String nodeStr(Node root) {
		String temp = "";
		if (root.getZero() == null && root.getOne() == null) {
			temp = "(\""+root.getSymbol()+"\")";
		}
		else {
			temp = "("+root.getFreq()+")";
		}
		return temp;
	}
	
	
	/** printDiagram - It prints the Huffman Tree at 270º turned. Internal nodes will print out their 
	 * 				frequencies and external nodes will print out the characters they hold. 
	 * 
	 * How it works - It uses an inorder traversal to print out the nodes. Otherwise, it will start printing the 
	 * 				tree line by line, layer by layer. Each layer will be a string. Each time it recurses, we check 
	 * 				for the root node. By implementing a level variable, it can detect the root node and makes sure 
	 * 				to only print the root value. If it is the root node, we do not add anything other than the space 
	 * 				and the node value.
	 * 				Then it determines whether we should concatenate an internal node value(frequency) or external 
	 * 				node value (character). This decision gets decided by a helper function. After the 
	 * 				layer has been constructed and the value from the node, we print this and continue until it 
	 * 				reaches the most left side of the tree.
	 * 
	 * @param node 			- A Node object, used to print the value (frequency or character) and traverse the tree
	 * @param hasRightChild - A boolean, it will switch from true and false within the function, depends on if a left
	 * 							node is found or a right node is present
	 * @param indent		- A string which will determine how far back the nodes will get printed at
	 * @param layer			- A string that will get constructed based on the node values, indentations, and branches
	 * @param level			- A integer that describes the level of the tree, used mostly to detect the root
	 */
	private void printDiagram(Node node, boolean hasRightChild, String indent, String layer, int level) {
		
		if (node.getZero() != null) {
			// The right side of the node is found, so hasRightChild is true
			String temp = hasRightChild ? "        ": " │      ";
			printDiagram(node.getZero(), true, indent+temp,"", level+1);
		}
		layer+= indent;
		if (hasRightChild && (level!=0)) {
			// Without level!=0, it could accidently print out an upper left corner 
			layer += " ┌";
		}
		else if (!hasRightChild && level!=0){
			// Without level!=0, it could accidently print out an lower left corner 
			layer += " └";
		}
		if (level!= 0) {
			// Adds a line, or branch
			layer+= "─────";
		}
		else if (level ==0) {
			// root is found, we align so the branches connect to the right ')'
			int dsplcemt = nodeStr(node).length() - 3;
			if (dsplcemt > 3) {
				for (int i=0; i< dsplcemt-3;i++) {
					layer+=" ";
				}
			}
			layer+="      ";
		}
		System.out.println(layer+nodeStr(node));
		if (node.getOne() != null) {
			String temp = hasRightChild&&(level!=0) ? " │      ": "        ";
			// The left side of the node is found, we set hasRightChild as false to explore
			// the left side of the tree
			printDiagram(node.getOne(), false, indent+temp,"",level+1);
		}
	}	
	
	// build optimal prefix-free code from message and make a huffman tree.
	private Node createTree(String message) {
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
			Node parent = new Node(child1.getFreq() + child2.getFreq(), child1, child2);
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
}
