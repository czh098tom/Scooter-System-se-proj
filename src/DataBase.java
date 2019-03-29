import java.util.ArrayList; //À´×ÔCrackedSkyµÄ²âÊÔ×¢ÊÍ...

public class DataBase {
    private ArrayList<User> users=new ArrayList<>();
    private ArrayList<Station> stations=new ArrayList<>();
    private ArrayList<Scooter> scooters=new ArrayList<>();
    private ArrayList<Transaction> transactions=new ArrayList<>();
    
    private static DataBase _current=null;
    
    public DataBase getCurrent() {
    	if(_current==null) {
    		_current=new DataBase();
    	}
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
}
