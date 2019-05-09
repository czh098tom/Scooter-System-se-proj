package gui;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;

import data.Station;
/*Take frame*/
/**
 * @author Jiansen Song
 * */
public class TakeFrame extends StateFrame{
	private static JLabel timer=new JLabel("Only one minute can be used to take the scooter",JLabel.CENTER);
	private JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	
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
		new TimerThread().start();
	}
	
	public static void main(String[] args) {
		User_Interface dockStation1=new User_Interface(1);
		dockStation1.setState(new TakeFrame(dockStation1));
	}
	
	class TimerThread extends Thread{
		
		public void run() {
			int countDown=60;
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				int i=0;
				public void run() {
					TakeFrame.timer.setText(String.format("Only %d seconds left", countDown-i));
					i++;
				}
			},0,1000);
			try {
				TimeUnit.SECONDS.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timer.cancel();
			TakeFrame.timer.setText("Time runs out");
		}
		
	}
}
