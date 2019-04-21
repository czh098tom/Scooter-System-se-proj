package data;
import java.util.*;
import java.io.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public final class DataBase {
    private LinkedList<User> users=new LinkedList<>();
    private LinkedList<Station> stations=new LinkedList<>();
    private LinkedList<Scooter> scooters=new LinkedList<>();
    private LinkedList<Transaction> transactions=new LinkedList<>();
    
    private static DataBase _current=null;
    
    private DataBase() {}
    
    public void initialize() {
    	stations.push(new Station());
    	stations.push(new Station());
    	stations.push(new Station());
    }
    
    public static DataBase getCurrent() {
    	if(_current==null) {
    		_current=new DataBase();
    		readFromFile();
    	}
    	return _current;
    }
    
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
    
    public static DataBase getNew() {
    	_current=new DataBase();
    	return _current;
    }

    public User getUserByID(String userid){
    	LinkedList<User> lookup=new LinkedList<>();
        users.forEach((u)->{
            if(u.getID().equals(userid))lookup.add(u);
        });
        if(lookup.size()>0)return lookup.get(0);
        return null;
    }
    
    public void putScooter(int stationid,int slotid,String scooterid) {
    	stations.get(stationid).putScooter(new Scooter(scooterid),slotid);
    }
    
    public void takeScooter(String userid,int stationid,int slotid) {
    	Scooter s=stations.get(stationid).removeScooter(slotid);
    	transactions.add(new Transaction(Transaction.TYPE_TAKE,userid,s.getID()));
    }
    
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
    	return sum!=0;
    }
    
    public boolean userExists(String id) {
    	return getUserByID(id)==null;
    }
    
    public void regUser(User u) {
    	users.add(u);
    }
    
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
}
