package gui;
import data.Station;

import javax.swing.JButton;
import javax.swing.JTextField;
/*Take frame*/
public class TakeFrame extends StateFrame{
	JTextField timer=new JTextField("Timer");
	JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	
	protected TakeFrame(StateManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		timer.setEditable(false);
		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].addActionListener(this);
			super.register(slot[i], ()->new TakeScooterFrame(parent));
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.registerClosing(null);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TakeFrame(null);
	}
}
