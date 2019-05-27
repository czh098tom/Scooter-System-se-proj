package gui;

public class RegisterManagement extends StateManager{

	public static void main(String[] args) {
		RegisterManagement tm=new RegisterManagement();
    	tm.setState(new EnterIDFrame(tm));
		// TODO Auto-generated method stub

	}

}
