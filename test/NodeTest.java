// Authors: Elisa Zabalza, Chris Machado, Jose Chavez, Michael Sava
// Date: 11/21/23
// Class: CS 345
// File name: HuffmanEncodingTest.java
// Description: This program tests the different node aspects like creating the node, testing the frequency of the node,
// whether the currect symbol gets pulled up, whether zero and one are part of the node as correctly constructed, whether
// it compares the character values correctly, and whether the internal nodes are created correctly.

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Node;

class NodeTest {

	@Test
	void testCanCreateCharNode() {
		Node node = new Node('a', 100);
		assertNotNull(node);
	}
	
	@Test
	void testCanGetFreq() {
		Node node = new Node('a', 53);
		assertEquals(53, node.getFreq());
	}
	
	@Test
	void testCanGetSymbol() {
		Node node = new Node('a', 53);
		assertEquals('a', node.getSymbol());
	}
	
	@Test
	void testCanGetZero() {
		Node zero = new Node('a', 53);
		assertNull(zero.getZero());
		
		Node one = new Node('b', 53);
		Node parent = new Node(zero.getFreq() + one.getFreq(), zero, one);
		assertEquals(zero, parent.getZero());
		assertNull(zero.getZero());
	}
	
	@Test
	void testCanGetOne() {
		Node one = new Node('b', 53);
		assertNull(one.getOne());
		
		Node zero = new Node('a', 53);
		Node parent = new Node(zero.getFreq() + one.getFreq(), zero, one);
		assertEquals(one, parent.getOne());
		assertNull(one.getOne());
	}
	
	@Test
	void testCanCompareToChar() {
		Node node1 = new Node('a', 53);
		Node node2 = new Node('A', 53);
		Node node3 = new Node('b', 53);
		Node node4 = new Node(' ', 53);
		Node nullTerminatorNode = new Node('\0', 53);
		Node internal = new Node(node1.getFreq() + node4.getFreq(), node1, node4);
		
		assertTrue(node1.compareToChar(node2) > 0); // a > A
		assertTrue(node1.compareToChar(node3) < 0); // a < b
		assertTrue(node1.compareToChar(node4) > 0); // a > space
		assertTrue(node2.compareToChar(node1) < 0); // A < a
		assertTrue(node2.compareToChar(node3) < 0); // A < b
		assertTrue(node2.compareToChar(node4) > 0); // A > space
		assertTrue(node3.compareToChar(node1) > 0); // b > a
		assertTrue(node3.compareToChar(node2) > 0); // b > A
		assertTrue(node3.compareToChar(node4) > 0); // b > space
		assertTrue(node4.compareToChar(node1) < 0); // space < a
		assertTrue(node4.compareToChar(node2) < 0); // space < A
		assertTrue(node4.compareToChar(node3) < 0); // space < b
		
		// all internal nodes must have the smallest (lexicographically speaking)
		// symbol character possible: the null terminator. All
		// other characters should thus be greater than internal
		// nodes when comparing characters.
		assertTrue(internal.compareToChar(nullTerminatorNode) == 0);
		assertTrue(internal.compareToChar(node4) < 0);
		assertTrue(internal.compareToChar(node3) < 0);
		assertTrue(internal.compareToChar(node2) < 0);
		assertTrue(internal.compareToChar(node1) < 0);
	}
	
	@Test
	void testCanCreateInternalNode() {
		Node leftLeaf = new Node('a', 1);
		Node rightLeaf = new Node('b', 2);
		
		Node internal = new Node(leftLeaf.getFreq() + rightLeaf.getFreq(), leftLeaf, rightLeaf);
		assertNotNull(internal);
		assertEquals(3, internal.getFreq());
	}

}
