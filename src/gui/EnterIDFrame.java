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

public class EnterIDFrame extends StateFrame{


	protected EnterIDFrame(StateManager parent) {
		super(parent);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JButton submitButton=null;
		JLabel warnLabel=null; 
		JTextField IDText=null ;

	    warnLabel = new JLabel();
	    warnLabel.setBounds(100,50,500,70);
	    warnLabel.setText("Please enter your QM number: ");
	    warnLabel.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    panel.add(warnLabel);
	    		
	    IDText = new JTextField(20);
	    IDText.setBounds(100,160,500,70);
	    panel.add(IDText);
	       
	    submitButton=new JButton("submit");
	    submitButton.setFont(new Font(Font.DIALOG,Font.BOLD,30));
	    submitButton.addActionListener(this);
	    super.register(submitButton,()->new RegisterFrame(parent));
		super.registerClosing(()->new RegisterFrame(parent));
	    submitButton.setBounds(200,270,300,70);
		panel.add(submitButton);
		
		this.setSize(700,500);
	    this.setLocation(10, 10);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	

}
