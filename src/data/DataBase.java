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
     * Get the total fine count in this week.
     * @param userid : The ID of a given user.
     * @return An integer value of fine count.
     */
    public int sumFine(String userid) {
    	int count=0;
    	LocalDateTime now=LocalDateTime.now();
    	for(Transaction t : transactions) {
    		if(t.getUserID().equals(userid) && t.isSameDateOf(now)) {
    			if(t.isFine())count++;
    			//else if(t.isPayFine())count--;
    		}
    	}
    	return count;
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
        				totalMin+=Duration.between(selected.getDateTime(),t.getDateTime()).toMinutes();
    				}
    			}
    		}
    	}
    	return totalMin>120;
    }
    
    /**
     * Get the total usage minutes in this week.
     * @param userid : The id of a given user.
     * @return A long value in minutes.
     */
    public long sumWeeklyUsage(String userid) {
    	long totalMin=0;
    	LinkedList<Transaction> pending=new LinkedList<Transaction>();
    	LocalDateTime now=LocalDateTime.now();
    	for(Transaction t : transactions){
    		if(t.getUserID().equals(userid) && t.isSameWeekOf(now)) {
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
        				totalMin+=Duration.between(selected.getDateTime(),t.getDateTime()).toMinutes();
    				}
    			}
    		}
    	}
    	return totalMin;
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
    	return sum>0;
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
     * Get weekly report data of a given user.
     * @param userid : A String object of userID.
     * @return An object typed {@link WeeklyReportData}, null if nothing is find
     */
    public WeeklyReportData getReportDataOf(String userid){
    	ArrayList<WeeklyReportData> list=new ArrayList<>();
    	String id;
    	for(User u:users) {
    		id=u.getId();
    		if(id.equals(userid)) {
        		list.add(new WeeklyReportData(u.getName(),id,sumWeeklyUsage(id),sumFine(id),u.getEmail()));
    		}
    	}
    	if(list.size()>0) {
        	return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Get weekly report data.
     * @return An object typed {@link ArrayList<WeeklyReportData>}, not null;
     */
    public ArrayList<WeeklyReportData> getReportData(){
    	ArrayList<WeeklyReportData> list=new ArrayList<>();
    	String id;
    	for(User u:users) {
    		id=u.getId();
    		list.add(new WeeklyReportData(u.getName(),id,sumWeeklyUsage(id),sumFine(id),u.getEmail()));
    	}
    	return list;
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
    
    /**
     * Get the transactions stored in this database.
     * @return A object typed {@link:LinkedList<Transaction>}, not null.
     */
    public LinkedList<Transaction> getTransactions(){
    	return transactions;
    }
    
    /**
     * Get the station stored in this database by its ID.
     * @param id : The ID of the target station.
     * @return A object typed {@link:Station}, not null.
     */
    public Station getStationByID(int id){
    	return stations.get(id);
    }
    
    /**Author : YSY
     * This is a 'users' linked list getter
     * @return the linked list object 'users'
     */
    public LinkedList<User> getUsers() {
    	return users;
    }
}
