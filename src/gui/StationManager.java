package gui;
/**
 * It's boundary class, provide the entry for Station UI
 * It's an inheritance from the StateManager class
 * @author Jiansen Song
 * @version 2.0
 */
public class StationManager extends StateManager {
	/**Store the docking station ID*/
	private int dockStationID;
	/**Store the slot ID*/
	private int slotID;
	/**Store the user ID who use the station currently*/
	private String userID;
	/**
	 * Initial the StationEntryFrame with station ID
	 * @param id : The ID of station
	 */
	public StationManager(int id) {
		this.dockStationID=id;
	}
	/**
	 * @return the ID of station
	 */
	public int getStationID() {
		return this.dockStationID;
	}
	/**
	 * @return the ID of user
	 */
	public String getUserID() {
		return this.userID;
	}
	/**
	 * To set the ID of user
	 * @param value : the user's ID should be valid
	 */
	public void setUserID(String value) {
		this.userID=value;
	}
	/**
	 * @return the ID of slot
	 */
	public int getSlotID() {
		return this.slotID;
	}
	/**
	 * To set the ID of slot
	 * @param value : the slot's ID should be valid
	 */
	public void setSlotID(int value) {
		this.slotID=value;
	}
	
	public static void main(String[] args) {
		int id=0;
		boolean modified=false;
		try {
			if(args!=null&&args.length>0)
			{
				int tmp=Integer.parseInt(args[0]);
				if(tmp<3) 
				{
					id=tmp;
					modified=true;
				}
			}
		}
		finally {
			if(!modified) System.out.println("Station does not given ot does not exist."
					+" Will enter Station 0 instead.");
		}
		StationManager dockStation0=new StationManager(id);
		dockStation0.setState(new StationInputIDFrame(dockStation0));
	}
}
