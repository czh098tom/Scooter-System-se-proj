package gui;

import javax.swing.JButton;

/**
 * @author Jiansen Song
 * */
public class TakeScooterFrame extends StateFrame{
	private JButton ok=new JButton("Ok");
	private JButton cancel=new JButton("Cancel");
	public TakeScooterFrame(User_Interface parent){
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		
		
	}
	
}
