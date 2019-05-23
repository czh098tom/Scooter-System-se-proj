package data;

/**
 * Controller for take operation.
 * @author Zihao Chen
 */
public class TakeContract {

	/**
	 * Store ID of the user.
	 */
	private final User user;
	/**
	 * Store the target station.
	 */
	private final Station station;
	/**
	 * Store target slot in target station.
	 */
	private final int slotID;
	
	/**
	 * Initialize the contract with user ID, station ID, and slot ID in target station.
     * @param userID : The id of operator user.
     * @param stationID : The id of target station.
     * @param slotID : The target slot ID in the station, from 1 to {@link Station}.SCOOTERCOUNT-1. 
	 */
	public TakeContract(String userID, int stationID, int slotID) {
		DataBase db=DataBase.getCurrent();
		this.station=db.getStationByID(stationID);
		this.slotID=slotID;
		this.user=db.getUserByID(userID);
	}
	
    /**
     * Take a scooter from a station. This will trigger transaction record.
     */
    public void takeScooter() {
    	String s=station.removeScooter(slotID);
    	DataBase.getCurrent().getTransactions().add(new Transaction(Transaction.TYPE_TAKE,user.getId(),s));
    	DataBase.getCurrent().writeToFile();
    }
}
