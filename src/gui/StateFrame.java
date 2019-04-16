package gui;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class StateFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Object,StateFrame> commands=new HashMap<Object,StateFrame>();
	
	private StateManager parent; 
	
	protected StateFrame(StateManager parent) {
		this.parent=parent;
	}
	
	protected void register(Object eventSource, StateFrame target) {
		commands.put(eventSource, target);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		parent.setState(commands.get(arg0.getSource()));
		this.dispose();
	}

}
