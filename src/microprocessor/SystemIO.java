package microprocessor;

import java.util.Scanner;

public class SystemIO extends IOManager {
	
	private Scanner s=new Scanner(System.in);
	
	public void setTo(byte value) {
		System.out.println((int)value);
	}
	
	public byte getFrom() {
		return s.nextByte();
	}
}
