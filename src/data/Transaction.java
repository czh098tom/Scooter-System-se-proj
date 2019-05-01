package data;

import java.util.*;
import java.time.*;

public class Transaction {
    public static final String NAN_ID="";

    public static final int TYPE_NA=-1;
    public static final int TYPE_TAKE=0;
    public static final int TYPE_RETURN=1;
    public static final int TYPE_FINE=2;
    public static final int TYPE_PAY_FINE=3;

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

    public boolean isFine(){
        return type==TYPE_FINE;
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
    
    public LocalDateTime getDateTime()
    {
    	return LocalDateTime.ofEpochSecond(transactionTime.getTime()/1000
    			, (int)(transactionTime.getTime()%1000)*1000000, DataBase.getLocalTimeZoneOffset());
    }
    
    public boolean isSameDateOf(LocalDateTime t) {
    	LocalDateTime date=getDateTime();
    	return t.getDayOfYear()==date.getDayOfYear() && t.getYear()==date.getYear();
    }
    
    public static void main(String[] args) {
    	Transaction t=new Transaction(Transaction.TYPE_TAKE,"00","00");
    	System.out.println(t.getDateTime().toString());
    }
}
