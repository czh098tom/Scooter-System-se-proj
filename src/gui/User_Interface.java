package gui;

public class User_Interface extends StateManager {
	private int dockStationID;
	public User_Interface(int id) {
		dockStationID=id;
	}
	
	public int getID() {
		return this.dockStationID;
	}
}
