package gui;

public class StationEntryFrame extends StateManager {
	private int dockStationID;
	private int slotID;
	private String userID;
	
	public StationEntryFrame(int id) {
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
		StationEntryFrame dockStation0=new StationEntryFrame(0);
		dockStation0.setState(new StationInputIDFrame(dockStation0));
	}
}
