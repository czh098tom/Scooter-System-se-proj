package data;

/**
 * Store data of a station.
 * @author ZIHAO CHEN
 */
public class Station{
	/**
	 * Store the slot count of a station.
	 */
    public static final int SCOOTERCOUNT=8;
    /**
     * Store the scooters in the station.
     */
    private String[] scooters=new String[SCOOTERCOUNT];
    
    /**
     * Get the Scooters array in the station.
     * @return A scooter Array.
     */
    public String[] getScooters() {
    	return scooters;
    }
    
    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setScooters(String[] value) {
    	scooters=value;
    }

    /**
     * This method put a scooter into a slot.
     * @param s : The scooter to put.
     * @param i : The slot ID to insert.
     */
    public void putScooter(String s,int i){
        scooters[i]=s;
    }
    
    /**
     * This method removes a scooter from a slot.
     * @param i : The slot ID
     * @return An object typed Scooter. Can be null if slot is empty.
     */
    public String removeScooter(int i) {
    	String s=scooters[i];
    	scooters[i]=null;
    	return s;
    }
}
