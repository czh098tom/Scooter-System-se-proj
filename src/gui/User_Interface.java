package gui;

public class User_Interface extends StateManager {
	private int dockStationID;
	private int slotID;
	private String userID;
	
	public User_Interface(int id) {
		dockStationID=id;
	}
	
	public int getStationID() {
		return this.dockStationID;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public void setUserID(String value) {
		this.userID=value;
	}
	
	public int getSlotID() {
		return this.slotID;
	}
	
	public void setSlotID(int value) {
		this.slotID=value;
	}
	
	public static void main(String[] args) {
		User_Interface dockStation0=new User_Interface(0);
		dockStation0.setState(new TakeRetIDFrame(dockStation0));
	}
}
