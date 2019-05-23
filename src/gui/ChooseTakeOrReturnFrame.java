package gui;

import data.DataBase;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * @author Jiansen Song
 * */
public class ChooseTakeOrReturnFrame extends StateFrame{
	JLabel friendlyMessage=new JLabel("Welcome to use the scooter-sharing system",JLabel.CENTER);
	JButton take=new JButton("Take");
	JButton ret=new JButton("Return");
	
	protected ChooseTakeOrReturnFrame(StationEntryFrame parent) {
		super(parent);
		super.setResizable(false);
		super.setLayout(null);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(friendlyMessage);
		friendlyMessage.setBounds(0, 0, 750, 200);
		super.getContentPane().add(take);
		take.setBounds(50, 250, 200, 100);
		take.addActionListener(this);
		super.getContentPane().add(ret);
		ret.setBounds(500, 250, 200, 100);
		ret.addActionListener(this);
		
		super.register(take, ()->
		{
			if(DataBase.getCurrent().isUserTaking(parent.getUserID())) {
				JOptionPane.showMessageDialog(ChooseTakeOrReturnFrame.this
						, "You are currently taking a scooter! Please return first.");
				return ChooseTakeOrReturnFrame.this;
			}
			if(DataBase.getCurrent().isUnpaid(parent.getUserID())) {
				JOptionPane.showMessageDialog(ChooseTakeOrReturnFrame.this
						, "You are currently being fined! Please pay first.");
				return ChooseTakeOrReturnFrame.this;
			}
			if(DataBase.getCurrent().isTodayUsageOverFlow(parent.getUserID())) {
				JOptionPane.showMessageDialog(ChooseTakeOrReturnFrame.this
						, "You are currently overusing this system! Please come next day.");
				return ChooseTakeOrReturnFrame.this;
			}
			return new TakeChooseSlotFrame(parent);
		});
		super.register(ret, ()->
		{
			if(!DataBase.getCurrent().isUserTaking(parent.getUserID())) {
				JOptionPane.showMessageDialog(ChooseTakeOrReturnFrame.this
						, "You are currently not taking a scooter!");
				return ChooseTakeOrReturnFrame.this;
			}
			return new ReturnChooseSlotFrame(parent);
		});
		
		super.registerClosing(()->new StationInputIDFrame(parent));
		//super.setVisible(true);
	}
}
