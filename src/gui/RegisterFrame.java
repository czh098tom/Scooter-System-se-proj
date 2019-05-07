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

public class RegisterFrame extends StateFrame{

	protected RegisterFrame(StateManager parent) {
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
	    
		JTextField nameText,emailText=null ;
		
		nameText = new JTextField(20);
		nameText.setBounds(330,130,500,70);
	    panel.add(nameText);
	       
	    emailText = new JTextField(20);
	    emailText.setBounds(330,240,500,70);
	    panel.add(emailText);
	    
	    submitButton=new JButton("submit");
	    submitButton.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    submitButton.addActionListener(this);
	    submitButton.setBounds(255,360,300,70);
		panel.add(submitButton);
		
		this.setSize(1000,500);
	    this.setLocation(10, 10);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	

}
