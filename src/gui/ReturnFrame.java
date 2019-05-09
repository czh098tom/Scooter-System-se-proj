package gui;
import data.Station;

import javax.swing.JButton;
/*Return frame*/
/**
 * @author Jiansen Song
 * */
public class ReturnFrame extends StateFrame {
	JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	public ReturnFrame(StateManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		
		for(int i=0;i<8;i++) {
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		
		super.registerClosing(null);
		super.setVisible(true);
		
	}
}
