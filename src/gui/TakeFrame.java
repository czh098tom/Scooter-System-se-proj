package gui;
import javax.swing.JButton;
import javax.swing.JLabel;

import data.Station;
/*Take frame*/
/**
 * @author Jiansen Song
 * */
public class TakeFrame extends StateFrame{
	JLabel timer=new JLabel("Only one minute can be used to take the scooter",JLabel.CENTER);
	JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	
	protected TakeFrame(User_Interface parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].addActionListener(this);
			slot[i].setEnabled(false);
			super.register(slot[i], ()->new TakeScooterFrame(parent));
			slot[i].setBounds(i*93, 200, 93, 100);
		}
//		boolean[] isAvaiable=DataBase.getCurrent().getStationState(parent.getID());
//		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
//			if(isAvaiable[i]) slot[i].setEnabled(true);
//		}
		super.registerClosing(null);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TakeFrame(null);
	}
}
