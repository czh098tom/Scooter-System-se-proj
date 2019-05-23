package gui;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.DataBase;
import data.Station;
/*Take frame*/
/**
 * @author Jiansen Song
 * */
public class TakeChooseSlotFrame extends StateFrame{
	private static JLabel timer=new JLabel("Only one minute can be used to take the scooter",JLabel.CENTER);
	private JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	
	protected TakeChooseSlotFrame(StationEntryFrame parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		boolean[] states=DataBase.getCurrent().getStationState(parent.getStationID());
		for(int i=0;i<Station.SCOOTERCOUNT;i++) {
			final int k=i;
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].addActionListener(this);
			slot[i].setEnabled(states[i]);
			super.register(slot[i], ()->{
				parent.setSlotID(k);
				return new TakeScooterFrame(parent);
			});
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.registerClosing(()->new ChooseTakeOrReturnFrame(parent));
	}
}
