package microprocessor;

import java.util.Scanner;

public class SystemIO extends IOManager {
	
	private Scanner s=new Scanner(System.in);
	
	public void setTo(byte value) {
		System.out.println(String.format("%d, 0x%x", value, value));
	}
	
	public byte getFrom() {
		byte b=s.nextByte();
		System.out.println(String.format("read byte: 0x%x/%d, char: %c", b, b, b));
		return b;
	}
}
