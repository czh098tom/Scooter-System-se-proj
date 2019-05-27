package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import data.DataBase;
import data.User;

public class RegisterFrame extends StateFrame{

	JTextField nameText,emailText=null ;
	protected RegisterFrame(StateManager parent,String userID) {
		super(parent);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JButton submitButton=null;
		JLabel nameLabel,emailLabel,warnLabel=null; 
		
		nameLabel = new JLabel();
		nameLabel.setBounds(20,130,300,70);
		nameLabel.setText("Full Name: ");
		nameLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(nameLabel);
	    
	    emailLabel = new JLabel();
	    emailLabel.setBounds(20,240,300,70);
	    emailLabel.setText("Email: ");
	    emailLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(emailLabel);
	    
	    warnLabel = new JLabel();
	    warnLabel.setBounds(20,20,600,70);
	    warnLabel.setText("Please enter your details: ");
	    warnLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(warnLabel);
	    
		
		
		nameText = new JTextField(20);
		nameText.setBounds(330,130,500,70);
	    panel.add(nameText);
	       
	    emailText = new JTextField(20);
	    emailText.setBounds(330,240,500,70);
	    panel.add(emailText);
	    
	    submitButton=new JButton("submit");
	    submitButton.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    submitButton.addActionListener(this);
	    super.register(submitButton,()->
	    {
	    	String fullName=nameText.getText();
	    	String email=emailText.getText();
	    	if(!User.checkEmail(email)) {
	    		Object[] options ={ "Re-enter", "Exit" }; 
		    	int m = JOptionPane.showOptionDialog(null, "Your mailbox format is incorrect! Please re-enter it.", "ERROR",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(m!=0) {
                	System.exit(0);
                }
	    		else {
	    			emailText.setText("");
			    	return RegisterFrame.this;
	    		}
	    	}
	    	
	    	User newUser=new User(userID,fullName,email);
	    	DataBase database=DataBase.getCurrent();
	    	database.regUser(newUser);
	    	database.writeToFile();
	    	
	    	return new PersonalFrame(parent,newUser);
	    });
	    submitButton.setBounds(255,360,300,70);
		panel.add(submitButton);
		
		this.setSize(1000,500);
	    this.setLocation(600, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	

}