package gui;

public class RegisterManager extends StateManager{

	private String userID;
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String id) {
		this.userID=id;
	}
	
	public static void main(String[] args) {
		RegisterManager tm=new RegisterManager();
    	tm.setState(new EnterIDFrame(tm));
		// TODO Auto-generated method stub

	}

}
