package microprocessor;

public class LoginInfo {
	private String userID;
	private boolean isToTake;
	private int targetSlot;
	
	public String getUserID() {
		return userID;
	}
	
	public boolean isToTake() {
		return isToTake;
	}
	
	public int getTargetSlot() {
		return targetSlot;
	}
	
	public LoginInfo(String userID, boolean isToTake, int targetSlot) {
		this.userID=userID;
		this.isToTake=isToTake;
		this.targetSlot=targetSlot;
	}
	
	public String toString() {
		return "User ID: "+userID+"\r\n"
				+(isToTake?"take":"return")+"\r\n"
				+"Target slot: "+targetSlot+"\r\n";
				
	}
}
