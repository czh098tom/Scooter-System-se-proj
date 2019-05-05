package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI2 extends StateFrame{
	JTextField jt1=new JTextField("Friendly Message");
	JButton jb1=new JButton("Take");
	JButton jb2=new JButton("Return");
	
	protected UI2(StateManager parent) {
		super(parent);
		super.getContentPane().add(jt1);
		super.getContentPane().add(jb1);
		super.getContentPane().add(jb2);
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Please select one slot");
				
			}
			
		});
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Take your time");
				
			}
			
		});
		super.setVisible(true);
	}
	
}
