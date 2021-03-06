package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;

import data.TakeContract;
/**
 * It's boundary class, let users to take scooter at any slot
 * It's an inheritance from the StateFrame class
 * @author Jiansen Song
 * @version 4.0
 */
public class TakeConfirmSlotFrame extends StateFrame{
	/**Timer to alarm the user*/
	private static JLabel timer=new JLabel("",JLabel.CENTER);
	/**Confirm to take the scooter*/
	private JButton ok=new JButton("Ok");
	/**Confirm not to take the scooter*/
	private JButton cancel=new JButton("Cancel");
	/**
	 * Initial the TakeScooterFrame with its parent
	 * @param parent : who is the frame belongs to
	 */
	public TakeConfirmSlotFrame(StationManager parent){
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
		
		timer.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		ok.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		cancel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		
		super.register(cancel, ()->new StationInputIDFrame(parent));
		super.registerClosing(()->new StationInputIDFrame(parent));
		super.setVisible(true);
		new TimerThread(this).start();
	}

	/**
	 * It's entity class, record the time
	 * It's an inheritance from the Thread class
	 * @author Jiansen Song
	 * @version 3.0
	 */
	class TimerThread extends Thread{
		private TakeConfirmSlotFrame parent;
		public TimerThread(TakeConfirmSlotFrame parent) {
			this.parent=parent;
		}
		public void run() {
			int countDown=60;
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				int i=0;
				public void run() {
					TakeConfirmSlotFrame.timer.setText(String.format("Only %d seconds left", countDown-i));
					if(i%2==0) {
						parent.ok.setBackground(Color.RED);
					}else parent.ok.setBackground(null);
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
			TakeConfirmSlotFrame.timer.setText("Time runs out");
			parent.ok.setEnabled(false);
		}
	
	}
}
