package gui;
import java.util.*;
import java.util.function.*;
import java.awt.event.*;

import javax.swing.JFrame;

public abstract class StateFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private HashMap<Object,Supplier<StateFrame>> commands=new HashMap<Object,Supplier<StateFrame>>();
	
	private StateManager parent; 
	
	protected StateFrame(StateManager parent) {
		this.parent=parent;
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	protected final void register(Object eventSource, Supplier<StateFrame> target) {
		commands.put(eventSource, target);
	}
	
	protected final void registerClosing(Supplier<StateFrame> target) {
		if(target!=null){
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
				
				public void windowClosed(WindowEvent e) {
					parent.setState(target.get());
				}
			});
		}
		else {
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}
	
	protected final StateManager getParentManager() {
		return parent;
	}
	
	@Override
	public final void actionPerformed(ActionEvent arg0) {
		parent.setState(commands.get(arg0.getSource()).get());
	}

}
