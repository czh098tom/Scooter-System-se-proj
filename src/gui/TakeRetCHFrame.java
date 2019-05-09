package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * @author Jiansen Song
 * */
public class TakeRetCHFrame extends StateFrame{
	JLabel friendlyMessage=new JLabel("Friendly Message",JLabel.CENTER);
	JButton take=new JButton("Take");
	JButton ret=new JButton("Return");
	
	protected TakeRetCHFrame(StateManager parent) {
		super(parent);
		super.setResizable(false);
		super.setLayout(null);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(friendlyMessage);
		friendlyMessage.setBounds(0, 0, 750, 200);
		super.getContentPane().add(take);
		take.setBounds(50, 250, 200, 100);
		take.addActionListener(this);
		super.getContentPane().add(ret);
		ret.setBounds(500, 250, 200, 100);
		ret.addActionListener(this);
		super.register(take, ()->new TakeFrame(parent));
		super.register(ret, ()->new ReturnFrame(parent));
		super.registerClosing(null);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TakeRetCHFrame(null);
	}
	
}
