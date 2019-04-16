package gui;

public abstract class StateManager {
	private StateFrame current;
	
	public void setState(StateFrame target) {
		current=target;
		current.setVisible(true);
	}
}
