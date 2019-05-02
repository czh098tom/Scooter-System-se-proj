package data;

/**
 * Store data of a scooter.
 * @author ZIHAO CHEN
 */
public class Scooter{
	/**
	 * Store the ID of a scooter.
	 */
    private String id="";
    
    /**
     * Default constructor for serialization. Never call this because ID is immutable.
     */
    public Scooter() {
    	
    }

    /**
     * Initializes the scooter with its ID.
     * @param id : The ID of a scooter, desired to be validated before.
     */
    public Scooter(String id){
        this.id=id;
    }

    /**
     * Get the ID of the scooter.
     * @return A String, indicating the ID.
     */
    public String getID(){
        return id;
    }
    
    /**
     * This method does nothing. Just JavaBean object needed.
     * @param id : No meanings.
     */
    public void setID(String value) {
    	//Cannot be called
    }
}
