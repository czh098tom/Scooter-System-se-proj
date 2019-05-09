package data;
import java.util.*;

import java.time.*;
import java.io.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

/**
 * Store all data that can be recorded. Singleton.
 * @author ZIHAO CHEN
 */
public final class DataBase {
	/**
	 * Store local time zone offset for time conversion.
	 */
	public static final ZoneOffset LOCAL_TIME_ZONE_OFFSET=OffsetDateTime.now().getOffset();
	
	/**
	 * Store data of users.
	 */
    private LinkedList<User> users=new LinkedList<>();
    /**
     * Store data of stations.
     */
    private LinkedList<Station> stations=new LinkedList<>();
    /**
     * Store data of scooters.
     */
    private LinkedList<Scooter> scooters=new LinkedList<>();
    /**
     * Store data of transactions.
     */
    private LinkedList<Transaction> transactions=new LinkedList<>();
    
    /**
     * Store the singleton instance of this object.
     */
    private static DataBase _current=null;
    
    /**
     * Hide default constructor.
     */
    private DataBase() {}
    
    /**
     * Initialize the database with three stations.
     */
    public void initialize() {
    	users.clear();
    	scooters.clear();
    	stations.clear();
    	stations.push(new Station());
    	stations.push(new Station());
    	stations.push(new Station());
    	transactions.clear();
    }
    
    /**
     * Get the singleton instance. If it does not exists, create one by reading from file.
     * @return An object of type {@link DataBase}, not null.
     */
    public static DataBase getCurrent() {
    	if(_current==null) {
    		_current=new DataBase();
    		readFromFile();
    	}
    	return _current;
    }
    
    /**
     * Read the User, Station, Scooter, Transaction data from file.
     */
    @SuppressWarnings("unchecked")
	private static void readFromFile() {
    	XMLDecoder decoder=null;
    	try {
        	FileInputStream fis=new FileInputStream("data/users.xml");
    		decoder=new XMLDecoder(fis);
    		_current.users=(LinkedList<User>)decoder.readObject();
        	fis=new FileInputStream("data/transactions.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.transactions=(LinkedList<Transaction>)decoder.readObject();
        	fis=new FileInputStream("data/stations.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.stations=(LinkedList<Station>)decoder.readObject();
        	fis=new FileInputStream("data/scooters.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.scooters=(LinkedList<Scooter>)decoder.readObject();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(decoder!=null)decoder.close();
    	}
    	if(_current==null) {
    		_current=new DataBase();
    	}
    }
    
    /**
     * Overwrite current instance of {@link DataBase} by a new one, then return it.
     * @return An object of type {@link DataBase}, not null.
     */
    public static DataBase getNew() {
    	_current=new DataBase();
    	return _current;
    }
    
    /**
     * Get a user by a given ID. 
     * It does not desire multiple user with same ID exists. If so, it will get the first user.
     * @param userid : The ID of the user.
     * @return An object of type {@link User}. if nothing is found, it will return null.
     */
    public User getUserByID(String userid){
    	LinkedList<User> lookup=new LinkedList<>();
    	for(User u:users)
    	{
    		if(userid.equals(u.getId()))
    			lookup.add(u);
    	}
        if(lookup.size()>0)return lookup.get(0);
        return null;
    }
    
    /**
     * Directly put a scooter from outside of the system to the system, triggering no transaction record.
     * @param stationid : The target station ID.
     * @param slotid : The target slot ID in the station, from 1 to {@link Station}.SCOOTERCOUNT-1. 
     * If it excesses this range, it will result in an error.
     * @param scooterid : The id of the scooter. Desired to be unique.
     */
    public void putScooter(int stationid,int slotid,String scooterid) {
    	scooters.add(new Scooter(scooterid));
    	stations.get(stationid).putScooter(scooterid,slotid);
    }
    
    /**
     * Check whether a given user taking a given scooter is overdue.
     * @param userid : The id of a given user.
     * @param scooterid : The id of a given scooter.
     * @return A boolean value. True for overdue, false for not overdue or not find a record.
     */
    public boolean isOverDue(String userid,String scooterid) {
    	Transaction lastwithsameid=null;
    	for(Transaction t : transactions){
    		if(t.getUserID().equals(userid) && t.getScooterID().equals(scooterid)
    				&& t.isTake())lastwithsameid=t;
    	}
    	if(lastwithsameid==null) {
    		return false;
    	}
    	else {
    		return LocalDateTime.now().minusMinutes(30).isAfter(lastwithsameid.getDateTime());
    	}
    }
    
    /**
     * Check whether a given user excess usage time TODAY.
     * @param userid : The id of a given user.
     * @return A boolean value. True for overdue, vice versa.
     */
    public boolean isTodayUsageOverFlow(String userid) {
    	long totalMin=0;
    	LinkedList<Transaction> pending=new LinkedList<Transaction>();
    	LocalDateTime now=LocalDateTime.now();
    	for(Transaction t : transactions){
    		if(t.getUserID().equals(userid) && t.isSameDateOf(now)) {
    			if(t.isTake()) {
    				pending.add(t);
    			}
    			else if(t.isReturn()) {
    				Transaction selected=null;
    				for(Transaction ts:pending) {
    					if(ts.getScooterID().equals(t.getScooterID()))selected=ts;
    				}
    				if(selected!=null) {
        				pending.remove(selected);
        				totalMin+=Duration.between(t.getDateTime(), selected.getDateTime()).toMinutes();
    				}
    			}
    		}
    	}
    	return totalMin>120;
    }
    
    /**
     * Check whether a given user have fine to pay.
     * @param userid : The ID of a given user.
     * @return A boolean value, True for unpaid.
     */
    public boolean isUnpaid(String userid) {
    	int count=0;
    	for(Transaction t : transactions) {
    		if(t.getUserID().equals(userid)) {
    			if(t.isFine())count++;
    			else if(t.isPayFine())count--;
    		}
    	}
    	return count>0;
    }
    
    /**
     * Get a scooter object by its ID.
     * @param scooterid : The ID of target scooter.
     * @return A object with type {@link Scooter}, null if not find.
     */
    public Scooter getScooterByID(String scooterid) {
    	LinkedList<Scooter> lookup=new LinkedList<>();
        scooters.forEach((u)->{
            if(u.getID().equals(scooterid))lookup.add(u);
        });
        if(lookup.size()>0)return lookup.get(0);
        return null;
    }
    
    /**
     * Return a scooter to a station. If after operation the operator is fined, return True.
     * @param userid : The ID of operator.
     * @param scooterid : The ID of a scooter.
     * @param stationid : The ID of the target station.
     * @param slotid : The ID of the target slot in target station.
     * @return A boolean value, True for after this operation, the operator is fined.
     */
    public boolean returnScooter(String userid,String scooterid,int stationid,int slotid) {
    	boolean isFined=false;
    	
    	Scooter s=getScooterByID(scooterid);
    	if(s!=null) {
        	stations.get(stationid).putScooter(s.getID(), slotid);
        	if(isOverDue(userid,scooterid)) {
        		transactions.add(new Transaction(Transaction.TYPE_FINE,userid,Transaction.NAN_ID));
        		isFined=true;
        	}
        	transactions.add(new Transaction(Transaction.TYPE_RETURN,userid,scooterid));
        	if(isTodayUsageOverFlow(userid)) {
        		transactions.add(new Transaction(Transaction.TYPE_FINE,userid,Transaction.NAN_ID));
        		isFined=true;
        	}
    	}
    	
    	return isFined;
    }
    
    /**
     * This method gets the state of a given station.
     * @param stationid : ID of the station, must be validated before.
     * @return A boolean array. Each represents a state of a single slot. 
     * True for slot that is occupied.
     */
    public boolean[] getStationState(int stationid) {
    	boolean[] states=new boolean[Station.SCOOTERCOUNT];
    	for(int i=0;i<Station.SCOOTERCOUNT;i++) {
    		states[i]=stations.get(stationid).getState(i);
    	}
    	return states;
    }
    
    /**
     * Take a scooter from a station. This will trigger transaction record.
     * @param userid : The id of operator user.
     * @param stationid : The id of target station.
     * @param slotid : The target slot ID in the station, from 1 to {@link Station}.SCOOTERCOUNT-1. 
     */
    public void takeScooter(String userid,int stationid,int slotid) {
    	String s=stations.get(stationid).removeScooter(slotid);
    	transactions.add(new Transaction(Transaction.TYPE_TAKE,userid,s));
    }
    
    /**
     * Check whether user is taking a scooter.
     * @param userid : The id of operator user.
     * @return A boolean value, True for user is taking any scooters.
     */
    public boolean isUserTaking(String userid) {
    	int sum=0;
    	for(Transaction u:transactions) {
    		if(u.getUserID().equals(userid)) {
    			if(u.isTake()) {
    				sum++;
    			}
    			else if(u.isReturn()){
    				sum--;
    			}
    		}
    	}
    	return sum>=0;
    }
    
    /***
     * Check whether user with a given ID exists.
     * @param id : The target id.
     * @return A boolean value. True for exist.
     */
    public boolean userExists(String id) {
    	return getUserByID(id)!=null;
    }
    
    /**
     * Register a {@link User} to current database.
     * @param u : A validated user.
     */
    public void regUser(User u) {
    	users.add(u);
    }
    
    /**
     * Write current {@link DataBase} to file.
     */
    public void writeToFile() {
    	XMLEncoder encoder=null;
    	try {
    		FileOutputStream fos=new FileOutputStream("data/users.xml");
    		encoder=new XMLEncoder(fos);
    		encoder.writeObject(this.users);
    		fos=new FileOutputStream("data/transactions.xml");
    		encoder.close();
    		encoder=new XMLEncoder(fos);
    		encoder.writeObject(this.transactions);
    		fos=new FileOutputStream("data/stations.xml");
    		encoder.close();
    		encoder=new XMLEncoder(fos);
    		encoder.writeObject(this.stations);
    		fos=new FileOutputStream("data/scooters.xml");
    		encoder.close();
    		encoder=new XMLEncoder(fos);
    		encoder.writeObject(this.scooters);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if(encoder!=null)encoder.close();
    	}
    }
	
	public static void main(String[] args) {
//		/*
//    	//check Email format 1@2.3
//    	System.out.println(User.checkEmail("1@2.3"));//true
//    	System.out.println(User.checkEmail("@2.3"));//false
//    	System.out.println(User.checkEmail("1@.3"));//false
//    	System.out.println(User.checkEmail("1@2."));//false
//    	//check QMID format with only 9 digits
//    	System.out.println(User.checkQMID("123456789"));//true
//    	System.out.println(User.checkQMID("1234567890"));//false
//    	System.out.println(User.checkQMID("abcdefghi"));//false
//    	System.out.println(User.checkQMID("12345678"));//false
//    	*/
    	if(User.checkEmail("1@2.3")&&User.checkQMID("123456789")) {
    		//DataBase db=DataBase.getCurrent();
    		DataBase db=DataBase.getNew();
    		db.initialize();
    		db.regUser(new User("123456789","aaa","1@2.3"));
    		db.putScooter(0,0,"aaa");

    		db.takeScooter("123456789",0,0);
    		db.returnScooter("123456789","aaa",0,1);

    		System.out.println(db.userExists("123456789"));
    		db.writeToFile();
    	}
	}
}
