package gui;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import data.DataBase;
import data.Station;
/**
 * It's boundary class, let users to choose a slot
 * It's an inheritance from the StateFrame class
 * @author Jiansen Song
 * @version 3.0
 */
public class TakeChooseSlotFrame extends StateFrame{
	/**Show friendly message: Only one minute can be used to take the scooter*/
	private static JLabel timer=new JLabel("Only one minute can be used to take the scooter",JLabel.CENTER);
	/**To notify each slot*/
	private JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	/**
	 * Initial the TakeChooseSlotFrame with its parent
	 * @param parent : who is the frame belongs to
	 */
	protected TakeChooseSlotFrame(StationManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		
		timer.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		
		boolean[] states=DataBase.getCurrent().getStationState(parent.getStationID());
		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
			final int k=i;
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].addActionListener(this);
			slot[i].setEnabled(states[i]);
			slot[i].setFont(new Font(Font.DIALOG,Font.BOLD,20));
			super.register(slot[i], ()->{
				parent.setSlotID(k);
				return new TakeConfirmSlotFrame(parent);
			});
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.registerClosing(()->new ChooseTakeOrReturnFrame(parent));
	}
}
