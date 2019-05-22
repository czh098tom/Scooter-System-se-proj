package microprocessor;

public class MicroprocessorEntry {
	
	private static IOManager io=IOManager.getCurrent();
	
	public static void main(String[] args) {
		io.setTo(io.getFrom());
	}
}
