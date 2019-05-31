package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DataBase;
import data.User;
/**
 * It's boundary class, display user registration information
 * It's an inheritance from the StateFrame class
 * @author Ningning Wang
 * @version 3.0
 */
public class PersonalFrame extends StateFrame{
	/**
	 * Initial the PersonalFrame with its parent
	 * @param parent : who is the frame belongs to
	 * @param user : the user whose information is shown at this frame.
	 */
	protected PersonalFrame(StateManager parent,User user) {
		super(parent);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JButton exitButton=null;
		JLabel warnLabel,idLabel,nameLabel,emailLabel=null; 
		
		warnLabel = new JLabel();
	    warnLabel.setBounds(50,20,600,70);
	    warnLabel.setText("You have registered successfully.");
	    warnLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(warnLabel);
	    
	    idLabel = new JLabel();
	    idLabel.setBounds(100,120,600,70);
	    idLabel.setText("QM Number: "+user.getId());
	    idLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(idLabel);
	    
	    
		nameLabel = new JLabel();
		nameLabel.setBounds(100,220,600,70);
		nameLabel.setText("Full Name   : "+user.getName());
		nameLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(nameLabel);
	    
	    emailLabel = new JLabel();
	    emailLabel.setBounds(100,320,600,70);
	    emailLabel.setText("Email           : "+user.getEmail());
	    emailLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(emailLabel);
	    
	    exitButton=new JButton("exit");
	    exitButton.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    exitButton.addActionListener(this);
	    super.register(exitButton,()->
	    {
	    	System.exit(0);
	    	return null;
	    });
	    exitButton.setBounds(150,420,300,70);
		panel.add(exitButton);
	    
	    this.setSize(700,700);
	    this.setLocation(600, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	    
	    
		// TODO Auto-generated constructor stub
	}
	
	

}
