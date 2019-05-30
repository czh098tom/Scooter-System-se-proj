package gui;

public class RegisterManagement extends StateManager{

	private String userID;
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String id) {
		this.userID=id;
	}
	
	public static void main(String[] args) {
		RegisterManagement tm=new RegisterManagement();
    	tm.setState(new EnterIDFrame(tm));
		// TODO Auto-generated method stub

	}

}
