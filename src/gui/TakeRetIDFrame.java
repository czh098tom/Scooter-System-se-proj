package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.DataBase;
import data.User;
/**
 * @author Jiansen Song
 * */
public class TakeRetIDFrame extends StateFrame {
	private JTextField prompt1=new JTextField("Please enter your QM number:");
	private JTextField qmID=new JTextField();
	private JButton submit=new JButton("SUBMIT");
	
	protected TakeRetIDFrame(User_Interface parent) {
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
				return new TakeRetCHFrame(parent);
			}else {
				JOptionPane.showMessageDialog(this, "User is not existed"); 
				return TakeRetIDFrame.this;
			}
		});
		super.registerClosing(null);
		super.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		User_Interface dockStation1=new User_Interface(1);
		dockStation1.setState(new TakeRetIDFrame(dockStation1));
//		User_Interface dockStation2=new User_Interface();
//		dockStation2.setState(new TakeRetIDFrame(dockStation2));
//		User_Interface dockStation3=new User_Interface();
//		dockStation3.setState(new TakeRetIDFrame(dockStation3));
	}
}
