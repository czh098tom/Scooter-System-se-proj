package gui;
import data.DataBase;
import data.ReturnContract;
import data.Station;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * It's boundary class, let users to return a scooter
 * It's an inheritance from the StateFrame class
 * @author Jiansen Song
 * @version 4.0
 */
public class ReturnChooseSlotFrame extends StateFrame {
	/**To notify each slot*/
	private JButton[] slot=new JButton[Station.SCOOTERCOUNT];
	/**Show which slot has been chose*/
	private JLabel whichSlot=new JLabel("",JLabel.CENTER);
	/**Timer to alarm the user*/
	private static JLabel timer=new JLabel("",JLabel.CENTER);
	/**Confirm not to return the scooter*/
	private JButton cancel=new JButton("Cancel");
	/**
	 * Initial the ReturnChooseSlotFrame with its parent
	 * @param parent : who is the frame belongs to
	 */
	public ReturnChooseSlotFrame(StationManager parent) {
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
			slot[i].setFont(new Font(Font.DIALOG,Font.BOLD,20));
			super.getContentPane().add(slot[i]);
			slot[i].setEnabled(!states[i]);
			slot[i].addActionListener(this);
			super.register(slot[i], ()->{
				switch(new ReturnContract(parent.getUserID(), parent.getStationID(), k)
						.returnScooter()) {
						case ReturnContract.CURRENT_OVERDUE:
							JOptionPane.showMessageDialog(ReturnChooseSlotFrame.this
									, "Successfully returned. But you are fined "
									+"due to returning too late this time!");
							break;
						case ReturnContract.TODAY_OVERFLOW:
							JOptionPane.showMessageDialog(ReturnChooseSlotFrame.this
									, "Successfully returned. But you are fined "
									+"due to overusing the system today!");
							break;
						default:
							JOptionPane.showMessageDialog(ReturnChooseSlotFrame.this
									, "Successfully returned!");
				}
				return new StationInputIDFrame(parent);
			});
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		super.getContentPane().add(cancel);
		cancel.setBounds(125, 300, 500, 100);
		cancel.addActionListener(this);
		super.register(cancel, ()->new ChooseTakeOrReturnFrame(parent));
		
		timer.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		cancel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		
		super.registerClosing(()->new ChooseTakeOrReturnFrame(parent));
		new TimerThread(this).start();
	}
	/**
	 * It's entity class, record the time
	 * It's an inheritance from the Thread class
	 * @author Jiansen Song
	 * @version 3.0
	 */
	class TimerThread extends Thread{
		private ReturnChooseSlotFrame parent;
		public TimerThread(ReturnChooseSlotFrame parent) {
			this.parent=parent;
		}
		public void run() {
			int countDown=60;
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {
				int i=0;
				public void run() {
					ReturnChooseSlotFrame.timer.setText(String.format("Only %d seconds left", countDown-i));
					if(i%2==0) {
						parent.cancel.setBackground(Color.RED);
					}else parent.cancel.setBackground(null);
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
			ReturnChooseSlotFrame.timer.setText("Time runs out");
			for(int i=0,len=parent.slot.length;i<len;i++) {
				parent.slot[i].setEnabled(false);
			}
		}
		
	}
}
