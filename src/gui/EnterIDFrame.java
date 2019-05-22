package gui;

import data.DataBase;
import data.User;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class EnterIDFrame extends StateFrame{


	JTextField IDText=null ;

	protected EnterIDFrame(StateManager parent) {
		super(parent);
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		JButton submitButton=null;
		JLabel warnLabel=null; 

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
	    super.register(submitButton,()->
	    {
	    	
	    	String userID=IDText.getText();
	    	if(!User.checkQMID(userID)) {
	    		Object[] options ={ "Re-enter", "Exit" }; 
		    	int m = JOptionPane.showOptionDialog(null, "Your QM number format is incorrect! Please re-enter it.", "ERROR",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(m!=0) {
                	System.exit(0);
                }
	    		else {
	    			IDText.setText("");
			    	return EnterIDFrame.this;
	    		}
	    	}
	    	DataBase data=DataBase.getCurrent();
			if(data.userExists(userID)) {
				Object[] options ={ "Re-enter", "Exit" }; 
		    	int m = JOptionPane.showOptionDialog(null, "Your QM number has been registered! Please re-enter it! ", "ERROR",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if(m!=0) {
                	System.exit(0);
                }
	    		else {
	    			IDText.setText("");
			    	return EnterIDFrame.this;
	    		}
			}
	    	return new RegisterFrame(parent,IDText.getText());
	    });
		super.registerClosing(null);
	    submitButton.setBounds(200,270,300,70);
		panel.add(submitButton);
		
		this.setSize(700,500);
	    this.setLocation(600, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}
	

}
