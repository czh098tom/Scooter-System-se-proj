package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.DataBase;
import data.User;
/**
 * It's boundary class, let users to enter their ID
 * It's an inheritance from the StateFrame class
 * @author Jiansen Song
 * @version 3.0
 */
public class StationInputIDFrame extends StateFrame {
	/**Show friendly message: Please enter your QM number*/
	private JTextField prompt1=new JTextField("Please enter your QM number:");
	/**Get the QM ID of user*/
	private JTextField qmID=new JTextField();
	/**Check the ID whether is valid*/
	private JButton submit=new JButton("SUBMIT");
	/**
	 * Initial the StationInputIDFrame with its parent
	 * @param parent : who is the frame belongs to
	 */
	protected StationInputIDFrame(StationManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(prompt1);
		prompt1.setEditable(false);
		prompt1.setBounds(0, 0, 750, 100);
		super.getContentPane().add(qmID);
		qmID.setBounds(0, 100, 750, 200);
		super.getContentPane().add(submit);
		submit.setBounds(125, 300, 500, 100);
		submit.addActionListener(this);
		
		super.register(submit, ()->{
			String id=qmID.getText();
			DataBase data=DataBase.getCurrent();
			if(User.checkQMID(id)&&data.userExists(id)) {
				parent.setUserID(id);
				return new ChooseTakeOrReturnFrame(parent);
			}else {
				JOptionPane.showMessageDialog(this, "User does not exist."); 
				return StationInputIDFrame.this;
			}
		});
		
		super.registerClosing(null);
	}
}
