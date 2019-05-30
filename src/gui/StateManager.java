package gui;

/**
 * Base class for all managers.
 * @author Zihao Chen
 */
public abstract class StateManager {
    /**
     * Store the only frame that manager takes.
     */
	private StateFrame current;

    /**
     * Set the frame the control takes, then show it.
     * @param target : The target frame.
     */
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
