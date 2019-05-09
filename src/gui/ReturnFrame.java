package gui;
import data.DataBase;
import data.Station;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/*Return frame*/
/**
 * @author Jiansen Song
 * 
 * */
public class ReturnFrame extends StateFrame {
	JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	JLabel whichSlot=new JLabel("",JLabel.CENTER);
	JButton cancel=new JButton("Cancel");
	public ReturnFrame(User_Interface parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(whichSlot);
		whichSlot.setBounds(0, 0, 750, 200);
		for(int i=0;i<8;i++) {
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].setEnabled(false);
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.getContentPane().add(cancel);
		cancel.setBounds(125, 300, 500, 100);
		cancel.addActionListener(this);
		super.register(cancel, ()->new TakeRetCHFrame(parent));
		boolean[] isAvaiable=new boolean[Station.SCOOTERCOUNT];
		isAvaiable=DataBase.getCurrent().getStationState(parent.getID());
		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
			if(!isAvaiable[i]) {
				whichSlot.setText(String.format("Please return the scooter to the %dth slot",i+1));
				slot[i].setEnabled(true);
				slot[i].addActionListener(this);
				super.register(slot[i], ()->{
//					returnScooter();
					return new TakeRetCHFrame(parent);
				});
				break;
			}
		}
		
		super.registerClosing(null);
		super.setVisible(true);
	}
	public static void main(String[] args) {
		User_Interface dockStation1=new User_Interface(1);
		dockStation1.setState(new ReturnFrame(dockStation1));
	}
}
