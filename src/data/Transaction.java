package data;

import java.util.Date;

public class Transaction {
    public static final String NAN_ID="";

    public static final int TYPE_NA=-1;
    public static final int TYPE_TAKE=0;
    public static final int TYPE_RETURN=1;
    public static final int TYPE_PAY_FINE=2;

    private int type=TYPE_NA;
    private String scooterID=NAN_ID;
    private String userID=NAN_ID;
    private Date transactionTime;
    
    public Transaction() {
    	
    }

    public Transaction(int type,String userID,String targetID){
        this.type=type;
        this.userID=userID;
        this.scooterID=targetID;
        this.transactionTime=new Date();
    }

    public void setType(int value){
    	
    }

    public void setScooterID(String value){
    	
    }

    public void setUserID(String value){
    	
    }

    public void setDate(Date value){
    	
    }

    public boolean isTake(){
        return type==TYPE_TAKE;
    }

    public boolean isReturn(){
        return type==TYPE_RETURN;
    }

    public boolean isPayFine(){
        return type==TYPE_PAY_FINE;
    }

    public int getType(){
        return type;
    }

    public String getScooterID(){
        return scooterID;
    }

    public String getUserID(){
        return userID;
    }

    public Date getDate(){
        return transactionTime;
    }
}
