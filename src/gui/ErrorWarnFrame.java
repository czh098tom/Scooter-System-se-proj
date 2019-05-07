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

public class ErrorWarnFrame extends StateFrame{


	protected ErrorWarnFrame(StateManager parent) {
		super(parent);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JButton reRegisterButton,cancelButton=null;
		JLabel warnLabel=null; 
        String warning="<html>ERROR <br/>" + 
  		"Your QM number is wrong!<br/>" + 
  		"Please re-enter it.";
		
	    warnLabel = new JLabel();
	    warnLabel.setBounds(100,50,300,150);
	    warnLabel.setText(warning);
	    warnLabel.setFont(new Font(Font.DIALOG,Font.BOLD,20));
	    panel.add(warnLabel);
	    		
	    reRegisterButton=new JButton("RE-Register");
	    reRegisterButton.setFont(new Font(Font.DIALOG,Font.BOLD,15));
	    reRegisterButton.addActionListener(this);
	    super.register(reRegisterButton,()->new EnterIDFrame(parent));
		super.registerClosing(()->new EnterIDFrame(parent));
		reRegisterButton.setBounds(100,210,150,50);
		panel.add(reRegisterButton);
		
		cancelButton=new JButton("Cancel");
		cancelButton.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		cancelButton.addActionListener(this);
		cancelButton.setBounds(300,210,100,50);
		panel.add(cancelButton);
		
		this.setSize(500,350);
	    this.setLocation(10, 10);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
	}
}
