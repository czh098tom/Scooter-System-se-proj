package data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Controller for return operation.
 * @author Zihao Chen
 */
public class ReturnContract {

	/**
	 * Indicate current usage is overdue.
	 */
	public static final int CURRENT_OVERDUE=1;
	/**
	 * Indicate today usage is overflow.
	 */
	public static final int TODAY_OVERFLOW=2;
	
	/**
	 * Store the target transaction list.
	 */
	private final LinkedList<Transaction> transactions;
	
	/**
	 * Store the target user ID.
	 */
	private final User user;
	/**
	 * Store the target scooter.
	 */
	private final Scooter scooter;

	/**
	 * Store the target station.
	 */
	private final Station station;
	/**
	 * Store the target slot in target station.
	 */
	private final int slotID;
	
	/**
	 * Initialize the contract with user ID, station ID, and slot ID in target station.
	 * @param userID : ID of the user. Must be validated before.
	 * @param stationID : ID of the station. Must be validated before.
	 * @param slotID : ID of the slot. Must be validated before.
	 */
	public ReturnContract(String userID, int stationID, int slotID) {
		DataBase db=DataBase.getCurrent();
		this.user=db.getUserByID(userID);
		transactions=db.getTransactions();
		this.scooter=db.getScooterByID(FurtherestTaking());
		this.station=db.getStationByID(stationID);
		this.slotID=slotID;
	}
	
	/**
     * Get the furtherest scooter ID which is taking by a given user.
     * @return A String object. Not null if find.
     */
    private String FurtherestTaking() {
    	ArrayList<String> pendingID=new ArrayList<>();
    	for(Transaction u:transactions) {
    		if(u.getUserID().equals(user.getId())) {
    			if(u.isTake()) {
    				pendingID.add(u.getScooterID());
    			}
    			else if(u.isReturn()){
    				for(String s:pendingID) {
    					if(s.equals(u.getScooterID())) {
    						pendingID.remove(s);
    						break;
    					}
    				}
    			}
    		}
    	}
    	if(pendingID.size()>0)return pendingID.get(0);
    	return null;
    }

    /**
     * Return a scooter to a station. If after operation the operator is fined, return True.
     * @return A integer value, CURRENT_OVERDUE for current fined overdue
     * , TODAY_OVERFLOW for usage of today overflow.
     */
    public int returnScooter() {
    	int isFined=0;
    	
    	if(scooter!=null) {
        	station.putScooter(scooter.getID(), slotID);
        	if(isOverDue()) {
        		transactions.add(new Transaction(Transaction.TYPE_FINE,user.getId(),Transaction.NAN_ID));
        		isFined=CURRENT_OVERDUE;
        	}
        	transactions.add(new Transaction(Transaction.TYPE_RETURN,user.getId(),scooter.getID()));
        	if(DataBase.getCurrent().isTodayUsageOverFlow(user.getId())) {
        		transactions.add(new Transaction(Transaction.TYPE_FINE,user.getId(),Transaction.NAN_ID));
        		isFined=TODAY_OVERFLOW;
        	}
    	}
    	DataBase.getCurrent().writeToFile();
    	
    	return isFined;
    }
    
    /**
     * Check whether a given user taking a given scooter is overdue.
     * @return A boolean value. True for overdue, false for not overdue or not find a record.
     */
    private boolean isOverDue() {
    	Transaction lastwithsameid=null;
    	for(Transaction t : transactions){
    		if(t.getUserID().equals(user.getId()) && t.getScooterID().equals(scooter.getID())
    				&& t.isTake())lastwithsameid=t;
    	}
    	if(lastwithsameid==null) {
    		return false;
    	}
    	else {
    		return LocalDateTime.now().minusMinutes(30).isAfter(lastwithsameid.getDateTime());
    	}
    }
}
