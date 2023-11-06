package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import model.HuffmanCoding;

class CompressionTest {

	@Test
	void testCanCorrectlyCompress() {
		String testInput = "aaa";
		String expectedOutput = "000";
		
		HuffmanCoding a = new HuffmanCoding();
		ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		a.compress(in, out);
		
		String actualOutput = new String(out.toByteArray());
		assertEquals(expectedOutput, actualOutput);
	}

}
