package gui;
import data.DataBase;
import data.Station;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/*Return frame*/
/**
 * @author Jiansen Song
 * 
 * */
public class ReturnFrame extends StateFrame {
	private JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	private JLabel whichSlot=new JLabel("",JLabel.CENTER);
	private static JLabel timer=new JLabel("",JLabel.CENTER);
	private JButton cancel=new JButton("Cancel");
	public ReturnFrame(User_Interface parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(whichSlot);
		whichSlot.setBounds(0, 0, 750, 100);
		super.getContentPane().add(timer);
		timer.setBounds(0, 100, 750, 100);
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
		new TimerThread().start();
	}
	public static void main(String[] args) {
		User_Interface dockStation1=new User_Interface(1);
		dockStation1.setState(new ReturnFrame(dockStation1));
	}
	
	class TimerThread extends Thread{
		
		public void run() {
			int countDown=60;
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				int i=0;
				public void run() {
					ReturnFrame.timer.setText(String.format("Only %d seconds left", countDown-i));
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
			ReturnFrame.timer.setText("Time runs out");
		}
		
	}
}
