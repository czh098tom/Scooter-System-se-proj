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
		boolean[] states=DataBase.getCurrent().getStationState(parent.getStationID());
		for(int i=0;i<8;i++) {
			final int k=i;
			slot[i]=new JButton("Slot "+(i+1));
			super.getContentPane().add(slot[i]);
			slot[i].setEnabled(!states[i]);
			slot[i].addActionListener(this);
			super.register(slot[i], ()->{
				DataBase db=DataBase.getCurrent();
				String scooter=db.FurtherestTaking(parent.getUserID());
				switch(db.returnScooter(parent.getUserID()
						, scooter, parent.getStationID(), k)) {
						case DataBase.CURRENT_OVERDUE:
							JOptionPane.showMessageDialog(ReturnFrame.this
									, "Successfully returned. But you are fined "
									+"due to returning too late this time!");
							break;
						case DataBase.TODAY_OVERFLOW:
							JOptionPane.showMessageDialog(ReturnFrame.this
									, "Successfully returned. But you are fined "
									+"due to overusing the system today!");
							break;
						default:
							JOptionPane.showMessageDialog(ReturnFrame.this
									, "Successfully returned!");
				}
				db.writeToFile();
				return new TakeRetIDFrame(parent);
			});
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.getContentPane().add(cancel);
		cancel.setBounds(125, 300, 500, 100);
		cancel.addActionListener(this);
		super.register(cancel, ()->new TakeRetCHFrame(parent));
		
		super.registerClosing(()->new TakeRetCHFrame(parent));
		//super.setVisible(true);
		new TimerThread().start();
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
