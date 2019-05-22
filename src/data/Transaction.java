package data;

import java.util.*;
import java.time.*;
import java.time.temporal.WeekFields;

/**
 * Store data of a transaction.
 * @author ZIHAO CHEN
 */
public class Transaction {
	
	/**
	 * Store week indicator.
	 */
	public static WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
	
	/**
	 * Store the default invalid ID of scooter ID or user ID
	 */
    public static final String NAN_ID="";

    /**
     * The default type of transaction.
     */
    public static final int TYPE_NA=-1;
    /**
     * Type indicating taking transaction.
     */
    public static final int TYPE_TAKE=0;
    /**
     * Type indicating returning transaction.
     */
    public static final int TYPE_RETURN=1;
    /**
     * Type indicating fining transaction.
     */
    public static final int TYPE_FINE=2;
    /**
     * Type indicating paying transaction.
     */
    public static final int TYPE_PAY_FINE=3;

    /**
     * Store type of the transaction.
     */
    private int type=TYPE_NA;
    /**
     * Store scooter ID of the transaction.
     */
    private String scooterID=NAN_ID;
    /**
     * Store user ID of the transaction.
     */
    private String userID=NAN_ID;
    /**
     * Store transaction time.
     */
    private Date transactionTime;
    
    /**
     * Default constructor for serialization. Never call this because data are immutable.
     */
    public Transaction() {
    	
    }

    /**
     * Initializes a transaction by type, operator ID and target ID.
     * @param type : Type of the transaction. See static variables.
     * @param userID : ID of the operator, desired to be validated before.
     * @param targetID : ID of the target, desired to be validated before.
     */
    public Transaction(int type,String userID,String targetID){
        this.type=type;
        this.userID=userID;
        this.scooterID=targetID;
        this.transactionTime=new Date();
    }
    
    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setType(int value){
    	this.type=value;
    }

    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setScooterID(String value){
    	this.scooterID=value;
    }

    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setUserID(String value){
    	this.userID=value;
    }

    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setDate(Date value){
    	this.transactionTime=value;
    }

    /**
     * Check whether the transaction is a transaction taking scooter.
     * @return A boolean value, True for transaction is taking.
     */
    public boolean isTake(){
        return type==TYPE_TAKE;
    }

    /**
     * Check whether the transaction is a transaction returning scooter.
     * @return A boolean value, True for transaction is returning.
     */
    public boolean isReturn(){
        return type==TYPE_RETURN;
    }

    /**
     * Check whether the transaction is a transaction fining.
     * @return A boolean value, True for transaction is fining.
     */
    public boolean isFine(){
        return type==TYPE_FINE;
    }
    
    /**
     * Check whether the transaction is a transaction paying fine.
     * @return A boolean value, True for transaction is paying fine.
     */
    public boolean isPayFine(){
        return type==TYPE_PAY_FINE;
    }

    /**
     * Get the transaction type.
     * @return An integer value type.
     */
    public int getType(){
        return type;
    }

    /**
     * Get the ID of target.
     * @return A String, ID of the target, desired to be validated before.
     */
    public String getScooterID(){
        return scooterID;
    }

    /**
     * Get the ID of operator.
     * @return A String, ID of the operator, desired to be validated before.
     */
    public String getUserID(){
        return userID;
    }

    /**
     * Get the Date of transaction.
     * @return An object typed {@link Date}.
     */
    public Date getDate(){
        return transactionTime;
    }

    /**
     * Get the Date of transaction.
     * @return An object typed {@link LocalDateTime}.
     */
    public LocalDateTime getDateTime()
    {
    	return LocalDateTime.ofEpochSecond(transactionTime.getTime()/1000
    			, (int)(transactionTime.getTime()%1000)*1000000, DataBase.LOCAL_TIME_ZONE_OFFSET);
    }

    /**
     * Check whether the transaction happens at the same date of a given date.
     * @param t : The given date.
     * @return A boolean value, True for same, vice versa.
     */
    public boolean isSameDateOf(LocalDateTime t) {
    	LocalDateTime date=getDateTime();
    	return t.getDayOfYear()==date.getDayOfYear() && t.getYear()==date.getYear();
    }
    
    /**
     * Check whether the transaction happens at the same week of a given date.
     * @param t : The given date.
     * @return A boolean value, True for same, vice versa.
     */
    public boolean isSameWeekOf(LocalDateTime t) {
    	LocalDateTime date=getDateTime();
    	return t.get(weekFields.weekOfYear())==date.get(weekFields.weekOfYear())
    			&& t.getYear()==date.getYear();
    }
    
    public static void main(String[] args) {
    	Transaction t=new Transaction(Transaction.TYPE_TAKE,"00","00");
    	System.out.println(t.getDateTime().toString());
    }
}
