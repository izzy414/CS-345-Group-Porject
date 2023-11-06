package model;

import java.io.IOException;
import java.io.OutputStream;

public class OutputBitstream {
	private OutputStream out;
	
	public OutputBitstream(OutputStream out) {
		this.out = out;
	}
	
	public void writeBoolean(boolean value) {
		char bit;
		if (value == true) {
			bit = '1';
		} else {
			bit = '0';
		}
		try {
			out.write(bit);
		} catch (IOException e) {}
	}
	
	public void writeCharacter(char character) {
		try {
			out.write(character);
		} catch (IOException e) {}
	}
	
	/// Future alternative to traversing the table and writing bits.
	//public void writeCode(HuffmanCode) {}
	
	public void close() {
		try {
			out.close();
		} catch (IOException e) {}
	}

}
