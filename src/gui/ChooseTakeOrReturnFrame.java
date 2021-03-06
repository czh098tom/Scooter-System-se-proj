package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.DataBase;
import data.PayFineContract;
/**
 * It's boundary class, let users to take or return the scooter
 * It's an inheritance from the StateFrame class
 * @author Jiansen Song
 * @version 3.0
 */
public class ChooseTakeOrReturnFrame extends StateFrame{
	/**Show friendly message: Welcome to use the scooter-sharing system*/
	JLabel friendlyMessage=new JLabel("Welcome to use the scooter-sharing system",JLabel.CENTER);
	/**Confirm to take the scooter*/
	JButton take=new JButton("Take");
	/**Confirm to return the scooter*/
	JButton ret=new JButton("Return");
	/**
	 * Initial the ChooseTakeOrReturnFrame with its parent
	 * @param parent : who is the frame belongs to
	 */
	protected ChooseTakeOrReturnFrame(StationManager parent) {
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
		
		take.setFont(new Font(Font.DIALOG,Font.BOLD,25));
		ret.setFont(new Font(Font.DIALOG,Font.BOLD,25));
		friendlyMessage.setFont(new Font(Font.DIALOG,Font.BOLD,30));
		
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
				PayFineContract pfc=new PayFineContract(parent.getUserID());
				double amount=pfc.sumUnpaidFine()*PayFineContract.singleFineAmount;
				int res=JOptionPane.showConfirmDialog(ChooseTakeOrReturnFrame.this
						, "You are currently being fined at amount of "
						+amount
						+" pounds, do you wish to pay the fine now?",null
						,JOptionPane.OK_CANCEL_OPTION);
				if(res==JOptionPane.OK_OPTION) {
					pfc.pay();
					JOptionPane.showMessageDialog(ChooseTakeOrReturnFrame.this
							, "Paid fine "+amount+" pounds.");
				}
				else {
					return ChooseTakeOrReturnFrame.this;
				}
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
	}
}
