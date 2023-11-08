package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import model.HuffmanEncoding;

class CompressionTest {

	@Test
	void testCanCorrectlyCompress() {
		String testInput = "aaa";
		String expectedOutput = "000";
		
		HuffmanEncoding encoding = new HuffmanEncoding();
		String actualOutput = encoding.compressThenExpand(testInput);
		
		assertEquals(expectedOutput, actualOutput);
	}

}
