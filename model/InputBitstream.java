package model;

import java.io.InputStream;
import java.util.Scanner;

public class InputBitstream {
	private Scanner scanner;
	
	public InputBitstream(InputStream in) {
		scanner = new Scanner(in);
	}
	
	// Read the next 32 "bits" and parse it as an integer.
	public int readInt() {
		String bits = scanner.next(".{32}");
		return Integer.parseInt(bits, 2);
	}
	
	// Read the next "bit" and parse it as a boolean.
	public boolean readBoolean() {
		String bit = scanner.next(".{1}");
		return Boolean.parseBoolean(bit);
	}
	
	public void close() {
		scanner.close();
	}
}
