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
    private Scooter[] scooters=new Scooter[SCOOTERCOUNT];
    
    /**
     * Get the Scooters array in the station.
     * @return A scooter Array.
     */
    public Scooter[] getScooters() {
    	return scooters;
    }
    
    /**
     * This method does nothing. Just JavaBean object needed.
     * @param value : No meanings.
     */
    public void setScooters(Scooter[] value) {
    	//Cannot be called
    }

    /**
     * This method put a scooter into a slot.
     * @param s : The scooter to put.
     * @param i : The slot ID to insert.
     */
    public void putScooter(Scooter s,int i){
        scooters[i]=s;
    }
    
    /**
     * This method removes a scooter from a slot.
     * @param i : The slot ID
     * @return An object typed Scooter. Can be null if slot is empty.
     */
    public Scooter removeScooter(int i) {
    	Scooter s=scooters[i];
    	scooters[i]=null;
    	return s;
    }
}
