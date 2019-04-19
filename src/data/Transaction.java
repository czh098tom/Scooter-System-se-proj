package data;
public abstract class Transaction {
    public static final String NANSCOOTER_ID="";

    private boolean isTake=false;
    private String scooterID=NANSCOOTER_ID;
    private String userID=NANSCOOTER_ID;

    public Transaction(boolean isTake,String userID,String targetID){
        this.isTake=isTake;
        this.userID=userID;
        this.scooterID=targetID;
    }

    public boolean isTake(){
        return isTake;
    }

    public String getScooterID(){
        return scooterID;
    }

    public String getUserID(){
        return userID;
    }
}
