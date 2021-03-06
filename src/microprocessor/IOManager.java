package microprocessor;

public abstract class IOManager {
	private static IOManager current=null;
	
	public static IOManager getCurrent() {
		if(current==null) {
			current=new SerialIO();
		}
		return current;
	}
	
	public abstract byte getFrom();
	
	public abstract void setTo(byte value);
	
	public void close() {
		
	}
}
