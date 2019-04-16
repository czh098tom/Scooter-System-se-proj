import java.util.ArrayList;
import java.io.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class DataBase {
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<Station> stations=new ArrayList<>();
    private ArrayList<Scooter> scooters=new ArrayList<>();
    private ArrayList<Transaction> transactions=new ArrayList<>();
    
    private int a=1;
    
    private static DataBase _current=null;
    
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
    		_current.users=(ArrayList<User>)decoder.readObject();
        	fis=new FileInputStream("data/transactions.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.transactions=(ArrayList<Transaction>)decoder.readObject();
        	fis=new FileInputStream("data/stations.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.stations=(ArrayList<Station>)decoder.readObject();
        	fis=new FileInputStream("data/scooters.xml");
    		decoder.close();
    		decoder=new XMLDecoder(fis);
    		_current.scooters=(ArrayList<Scooter>)decoder.readObject();
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

    public User getUserByID(String id){
        ArrayList<User> lookup=new ArrayList<>();
        users.forEach((u)->{
            if(u.getID().equals(id))lookup.add(u);
        });
        if(lookup.size()>0)return lookup.get(0);
        return null;
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
