// Author: Chris Machado, Isabel Zabalza, Jose Chavez, Michael Sava
// File name: HuffmanEncoding.java
// Date: 11/21/2023
// Class: CSC 345
// Purpose: The HuffmanEncoding class is the main class
//   for the program that takes the user input and uses
//   that along with the MinimumPriorityQueue class and
//   the Node class to print out compressed message,
//   decompressed message, binary tree, and table from
//   the user input.
package model;

import java.util.Scanner;

public class HuffmanEncoding {
	private int height;
	// Accepts the user input and sends it to the compressThenExpand function and
	// the createTree function while then
	// sending that data to the printTree function and the printTable function.
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String message = scanner.nextLine();
				String nonCompressedMessage = getBinaryString(message);
				HuffmanEncoding encoding = new HuffmanEncoding();
				String output = encoding.compressThenExpand(message);
				Node root = encoding.createTree(message);
				encoding.printTree(root);
				encoding.printTable(root);
				System.out.println(output);
			}
		}
	}

	/// Returns a string representation of the base-2 unsigned integer
	/// representation of the ASCII code of each character in [msg].
	public static String getBinaryString(String msg) {
		String str = "";
		for (char bit : msg.toCharArray()) {
			String binary = Integer.toBinaryString(bit);
			str += padBinaryString(binary);
		}
		return str;
	}
	
	// Takes a string and left-pads it with "0"s until the string
	// has a length of 8. The padded string is returned.
	private static String padBinaryString(String binary) {
		int diff = 8 - binary.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < diff; i++) {
			sb.append('0');
		}
		sb.append(binary);
		return sb.toString();
	}

	// Accepts the string as an argument, creates the tree based off that string and
	// sends both the root
	// and string to the compressMessage and decompressMessage functions before
	// printing out their results.
	public String compressThenExpand(String message) {
		Node root = createTree(message);
		String compressedMessage = compressMessage(root, message);
		String decompressMessage = decompressMessage(root, compressedMessage);
		String nonCompressedMessage = getBinaryString(message);
		return "\nCompressed Message: " + compressedMessage + "\nDecompressed Message: " + decompressMessage
				+ "\nOriginal encoding without compressing: " + nonCompressedMessage + "\n"
				+ (nonCompressedMessage.length() - compressedMessage.length())
				+ " characters longer than message compressed with huffman encoding";
	}

	// Accepts the root and string as arguments then uses a depth-first search
	// traversal (a.k.a the dfs function) to go
	// through the tree to get the codes for each letter, appending it to the
	// returned string.
	public String compressMessage(Node root, String message) {
		Node curNode = root;
		StringBuilder encodedMessage = new StringBuilder();
		for (char bit : message.toCharArray()) {
			encodedMessage = dfs(curNode, encodedMessage, bit, 0);
		}
		return encodedMessage.toString();
	}

	// Is the depth-first search traversal through the tree that takes the root, the
	// current string of encodedMessage,
	// the bit and the level it's at.
	private StringBuilder dfs(Node root, StringBuilder encodedMessage, char bit, int level) {
		Node curNode = root;
		if (curNode.getSymbol() != 0) {
			if (curNode.getSymbol() == bit) {
				if (level == 0) {
					encodedMessage.append('1');
				}
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

	// Takes the root and the string that was compressed as an argument to traverse
	// through the tree to decode the message.
	public String decompressMessage(Node root, String message) {
		StringBuilder decodedMessage = new StringBuilder();
		Node curNode = root;

		for (char bit : message.toCharArray()) {
			if (bit == '0') {
				curNode = curNode.getZero();
			} else if (bit == '1') {
				curNode = curNode.getOne();
			}
			if (curNode == null) {
				curNode = root;
			}
			if (curNode.getZero() == null && curNode.getOne() == null) {
				decodedMessage.append(curNode.getSymbol());
				curNode = root;
			}
		}
		return decodedMessage.toString();
	}

	/**
	 * Adds dummy values at the end of the encoded message to be removed by dfs once
	 * the correct character is found
	 */
	private void addDummyValues(StringBuilder encodedMessage, int level) {
		int i;
		for (i = 0; i < level; i++) {
			encodedMessage.append('$');
		}
	}
	
	/** findHeight - Finds the height of the tree
	 * @param root	- Node object, the root of the tree
	 * @return
	 */
	private void findHeight(Node root) {
		preOrder(root, 0);
	}
	
	/** preOrder - Does a preorder traversal to find the height of the tree,
	 * 				updates the height value
	 * @param root	- Node object, the root node of the tree 
	 * @param height- Integer, the current nodes height 
	 */
	private void preOrder(Node root,int height) {
		if (root == null) {
			return;
		}
		if (height > this.height) {
			this.height = height;
		}
		preOrder(root.getOne(), height+1);
		preOrder(root.getZero(), height+1);
	}
	
	/** printTable - Prints the table with the characters and its code
	 * @param root - Node type, the root of the tree
	 */
	public void printTable(Node root) {
		findHeight(root);
		System.out.println("**** TABLE *****");
		// Line1 is the top line
		// Line2 is the middle line of the table
		// Line3 is the bottom line of the table
		String line1 = "──────";
		String line2 = "──────";
		String line3 = "└──────┴";
		
		String space = " ";
		for (int i=0; i< height-4;i++) {
			line1+= "─";
			line2+= "─";
			space+= " ";
		}
		// Adds the last part of the line of the table
		line1+= "┐";
		line2+= "┤";
		space+= "│";
		
		for (int i=0; i<line1.length()-1;i++) {
			line3+= "─";
		}
		line3+= "┘";
		System.out.println("┌──────┬"+line1);
		System.out.println("│ Char │ Code"+space);
		System.out.println("├──────┼"+line2);
		preOrderPrint(root, "");
		System.out.println(line3);
	}
	
	/** preOrderPrint - Prints out the character with its code 
	 * @param root - Node, root of the tree
	 * @param code - String, code of the character
	 */
	private void preOrderPrint(Node root, String code) {
		if (root ==null) {
			return;
		}
		
		// Adds '1' to code
		if (root.getOne()!= null) {
			preOrderPrint(root.getOne(), code+"1");
		}
		
		// Adds '0' to code
		if (root.getZero()!= null) {
			preOrderPrint(root.getZero(), code+"0");
		}
		
		// A leaf node is found, so we print the character and code
		// i.e. | char | code |
		if (root.getOne()== null && root.getZero()== null) {
			
			// Case for when the tree is one node
			if (root.getParent()==null) {
				// Hardcodes code to '1'
				String format = format(height, "1");
				System.out.println("│ \""+root.getSymbol()+"\"  │  "+format);
			}
			else {
				String format = format(height, code);
				System.out.println("│ \""+root.getSymbol()+"\"  │ "+code+format);
			}
			return;
		}
	}

	/** format - Formats the code with the table length
	 * @param height 	- Height of the tree
	 * @param code		- Code of a character
	 * @return
	 */
	private String format(int height, String code) {
		String retVal = " ";
		int cdLen = code.length();
		for (int i=0; i<height-cdLen;i++) {
			retVal+=" ";
		}
		if (height < 4) {
			// Special case where the for statement never runs
			boolean flag = true;
			// When code length is 1 and the height is greater than 0
			if (cdLen==1 && height>0 ) {
				if (!(cdLen==1 && height ==1)) {
					cdLen++;
				}
			}
			else {
				if (height-1 == cdLen) {
					cdLen++;
				}
			}
			// Runs for statement after the code length has been "updated"
			for (int i=cdLen; i<4;i++) {
				retVal+=" ";
			}
		}
		retVal+= "│";
		return retVal;
	}
	
	// Takes the root of the tree as an argument and initializes the making of the tree by sending it to 
	// the printDiagram function.
	public void printTree(Node root) {
		String layer = "";
		System.out.println("****  TREE  ****");
		if (root == null) {
			return;
		}
		if (root.getOne() != null) {
			printDiagram(root,true, "", layer,0);
			System.out.println();
		}
		
		else if (root.getZero() != null) {
			printDiagram(root,false, "", layer,0);
			System.out.println();
		}
		
		else if(root.getZero()==null && root.getOne()==null) {
			printDiagram(root,false, "", layer,0);
			System.out.println();
		}
	}
	
	/** nodeStr - Returns a string value of either the frequency or a character
	 * @param root - Node object that is either an internal or external node
	 * @return	A string of the node's value
	 */
	private String nodeStr(Node root) {
		String retStr = "";
		if (root.getZero() == null && root.getOne() == null) {
			// External Node is passed in
			if (root.getParent() == null) {
				// Tree is made of only one node, we print the frequency then the character
				// i.e. (frequency) ───── (character)
				retStr = "("+root.getFreq()+")─────(\""+root.getSymbol()+"\")";
			}
			else {
				// Returns the character value formatted, "(symbol)"
				retStr = "(\""+root.getSymbol()+"\")";
			}
		}
		else {
			// Returns frequency value formatted, "(frequency)"
			retStr = "("+root.getFreq()+")";
		}
		return retStr;
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
			// Without level==0, it could mistakenly print out an upper left corner 
			layer += " ┌";
		}
		else if (!hasRightChild && level!=0){
			// Without level==0, it could mistakenly print out an lower left corner 
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
	public Node createTree(String message) {
		// We are skipping using a HashTable, like was done here:
		// https://aquarchitect.github.io/swift-algorithm-club/Huffman%20Coding/
		// Except instead of storing the character frequencies as nodes in an array
		// (like that article did), we will just store them as integers in an array
		// and each index of the 256-index array corresponds to a character code in
		// ASCII.

		// Count character frequencies and number of unique characters in the message
		int[] freqs = new int[256];
		int symbolCount = 0;
		for (int i = 0; i < message.length(); i++) {
			char asciiCode = message.charAt(i);
			if (freqs[asciiCode] == 0) {
				// We found a new character.
				symbolCount++;
			}
			freqs[asciiCode]++;
		}

		// Create a minimum priority queue of char nodes for every unique character,
		// sorted based on each character's frequency in the original message.
		MinimumPriorityQueue chars = new MinimumPriorityQueue(freqs, symbolCount);

		Node root = combineCharNodesAndBuildTree(chars);
		return root;
	}

	// Combines the character nodes and builds a Huffman tree until it gets to the
	// last node.
	private Node combineCharNodesAndBuildTree(MinimumPriorityQueue nodes) {
		if (nodes.isEmpty()) {
			return null;
		}

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
}
