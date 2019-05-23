package gui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;

import data.DataBase;
import data.TakeContract;

/**
 * @author Jiansen Song
 * */
public class TakeScooterFrame extends StateFrame{
	private static JLabel timer=new JLabel("",JLabel.CENTER);
	private JButton ok=new JButton("Ok");
	private JButton cancel=new JButton("Cancel");
	public TakeScooterFrame(StationEntryFrame parent){
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		super.getContentPane().add(ok);
		ok.setBounds(50, 250, 200, 100);
		ok.addActionListener(this);
		super.register(ok, ()->{
			new TakeContract(parent.getUserID(), parent.getStationID(), parent.getSlotID()).takeScooter();
			return new StationInputIDFrame(parent); 
		});
		super.getContentPane().add(cancel);
		cancel.setBounds(500, 250, 200, 100);
		cancel.addActionListener(this);
		super.register(cancel, ()->new TakeChooseSlotFrame(parent));
		super.registerClosing(null);
		super.setVisible(true);
		new TimerThread(this).start();
	}
	public static void main(String[] args) {
		StationEntryFrame dockStation1=new StationEntryFrame(1);
		dockStation1.setState(new TakeScooterFrame(dockStation1));	
	}
	class TimerThread extends Thread{
		private TakeScooterFrame parent;
		public TimerThread(TakeScooterFrame parent) {
			this.parent=parent;
		}
		public void run() {
			int countDown=60;
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				int i=0;
				public void run() {
					TakeScooterFrame.timer.setText(String.format("Only %d seconds left", countDown-i));
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
			TakeScooterFrame.timer.setText("Time runs out");
			parent.ok.setEnabled(false);
		}
	
	}
}
