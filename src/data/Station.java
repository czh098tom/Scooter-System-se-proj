package data;
public class Station{
    public static final int SCOOTERCOUNT=8;
    private Scooter[] scooters=new Scooter[SCOOTERCOUNT];
    
    public Scooter[] getScooters() {
    	return scooters;
    }
    
    public void setScooters(Scooter[] value) {
    }

    public void putScooter(Scooter s,int i){
        scooters[i]=s;
    }
    
    public Scooter removeScooter(int i) {
    	Scooter s=scooters[i];
    	scooters[i]=null;
    	return s;
    }
}
