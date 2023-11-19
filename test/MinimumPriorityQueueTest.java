package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.MinimumPriorityQueue;
import model.Node;

class MinimumPriorityQueueTest {

	@Test
	void testCanGetSize() {
		int[] freqs = new int[256];
		freqs['a'] = 3;
		freqs['b'] = 8;
		freqs['c'] = 1;
		freqs['d'] = 1;
		int symbolCount = 4;

		MinimumPriorityQueue pq = new MinimumPriorityQueue(freqs, symbolCount);
		assertEquals(symbolCount, pq.size());

		pq.dequeue();
		assertEquals(symbolCount - 1, pq.size());

		pq.dequeue();
		assertEquals(symbolCount - 2, pq.size());
	}

	@Test
	void testCanEnqueueOnlyAfterDequeueingAtLeastOnce() {
		int[] freqs = new int[256];
		freqs['a'] = 3;
		freqs['b'] = 8;
		freqs['c'] = 1;
		freqs['d'] = 1;
		int symbolCount = 4;

		MinimumPriorityQueue pq = new MinimumPriorityQueue(freqs, symbolCount);
		assertEquals(symbolCount, pq.size());

		try {
			// This enqueue doesn't work because the heap is full because we haven't
			// dequeued yet.
			pq.enqueue(new Node('e', 3));
			fail("Should have thrown an exception");
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		pq.dequeue();
		assertEquals(symbolCount - 1, pq.size());

		// This enqueue works.
		pq.enqueue(new Node('f', 4));
		assertEquals(symbolCount, pq.size());

		try {
			// This enqueue doesn't work.
			pq.enqueue(new Node('g', 4));
			fail("Should have thrown an exception");
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		pq.dequeue();
		assertEquals(symbolCount - 1, pq.size());
		pq.dequeue();
		assertEquals(symbolCount - 2, pq.size());

		// These enqueues work.
		pq.enqueue(new Node('h', 4));
		assertEquals(symbolCount - 1, pq.size());
		pq.enqueue(new Node('i', 4));
		assertEquals(symbolCount, pq.size());

		try {
			// This enqueue doesn't work.
			pq.enqueue(new Node('j', 4));
			fail("Should have thrown an exception");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	@Test
	void testCanDequeue() {
		int[] freqs = new int[256];
		freqs['a'] = 3;
		freqs['b'] = 8;
		freqs['c'] = 1;
		int symbolCount = 3;

		MinimumPriorityQueue pq = new MinimumPriorityQueue(freqs, symbolCount);

		assertEquals(3, pq.size());

		pq.dequeue();
		assertEquals(2, pq.size());

		pq.dequeue();
		assertEquals(1, pq.size());

		pq.dequeue();
		assertEquals(0, pq.size());
	}

	@Test
	void testDequeueOrderIsBasedOnLowestFrequencyFirstThenLexicographicallySmallestSymbol() {
		int[] freqs = new int[256];
		freqs['E'] = 5;
		freqs['a'] = 3;
		freqs['b'] = 8;
		freqs['c'] = 1;
		freqs['d'] = 1;
		int symbolCount = 5;
		
		MinimumPriorityQueue pq = new MinimumPriorityQueue(freqs, symbolCount);

		// Dequeue order is based on lowest frequency
		// first, then lexicographically smallest symbol:
		Node firstNode = pq.dequeue(); // node 'c'
		assertEquals(1, firstNode.getFreq());
		Node secondNode = pq.dequeue(); // node 'd'
		assertEquals(1, secondNode.getFreq());
		
		// Let's make an internal node for better testing
		// on that lowest frequency expectation.
		Node internalNode = new Node(firstNode.getFreq() + secondNode.getFreq(), firstNode, secondNode);
		assertEquals(2, internalNode.getFreq());
		pq.enqueue(internalNode);
		// The internalNode has a frequency of 2, so it should be the next node to dequeue.
		assertEquals(internalNode, pq.dequeue());
		
		// Continue checking dequeue order:
		assertEquals(3, pq.dequeue().getFreq()); // node 'a'
		assertEquals(5, pq.dequeue().getFreq()); // node 'E'
		assertEquals(8, pq.dequeue().getFreq()); // node 'b'
	}

	@Test
	void testDoesNotFailWhenQueueContainsEveryAsciiCharPossible() {
		int[] freqs = new int[256];
		for (char asciiCode = 0; asciiCode < 256; asciiCode++) {
			freqs[asciiCode] = 3;
		}
		int symbolCount = 256;

		MinimumPriorityQueue pq = new MinimumPriorityQueue(freqs, symbolCount);
		assertEquals(symbolCount, pq.size());
	}
	
	@Test
	void testCanCreateAnEmptyMinimumPriorityQueue() {
		int[] freqs = new int[256];
		try {
			new MinimumPriorityQueue(freqs, 0);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail("we should have handled when all character frequencies are zero.");
		}
	}

}
