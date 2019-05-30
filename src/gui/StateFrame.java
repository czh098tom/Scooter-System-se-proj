package gui;
import java.util.*;
import java.util.function.*;
import java.awt.event.*;

import javax.swing.JFrame;

/**
 * Base class for all {@link JFrame} that represent a state in {@link Control}.
 * @author Zihao Chen
 */
public abstract class StateFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
    /**
     * Store the state transition table when invoking Action event.
     */
	private HashMap<Object,Supplier<StateFrame>> commands=new HashMap<Object,Supplier<StateFrame>>();

    /**
     * Store the parent {@link Control}.
     */
	private StateManager parent; 

    /**
     * Store whether the JFrame is disposing manually (by clicking X) or invoked by transition.
     */
	private boolean isDisposingManually=true;
	
    /**
     * Initialize the Frame with parent control.
     * @param parent the parent control.
     */
	protected StateFrame(StateManager parent) {
		this.parent=parent;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

    /**
     * Register a transition.
     * @param eventSource : The object invoking Action event.
     * @param target : The anonymous inner class that had a function returning the transition target.
     */
	protected final void register(Object eventSource, Supplier<StateFrame> target) {
		commands.put(eventSource, target);
	}
	
    /**
     * Register operation when clicking X manually.
     * @param target : The anonymous inner class that had a function returning the transition target.
     *               Returning null will cause the program exit.
     */
	protected final void registerClosing(Supplier<StateFrame> target) {
		if(target!=null){
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
				
				public void windowClosed(WindowEvent e) {
					if(isDisposingManually)parent.setState(target.get());
				}
			});
		}
		else {
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
	
    /**
     * This method gets the parent control of this frame.
     * @return An object typed {@link Control}.
     */
	protected final StateManager getParentManager() {
		return parent;
	}
	
    /**
     * Deal with Action event.
     * @param arg0 : the event argument.
     */
	@Override
	public final void actionPerformed(ActionEvent arg0) {
		isDisposingManually=false;
		parent.setState(commands.get(arg0.getSource()).get());
	}
}
