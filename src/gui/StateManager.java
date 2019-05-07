package gui;

public abstract class StateManager {
	private StateFrame current;
	
	public void setState(StateFrame target) {
		if(target!=current) {
			if(current!=null)
			{
				current.dispose();
			}
			current=target;
			current.setVisible(true);
		}
	}
}
