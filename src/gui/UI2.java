package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI2 extends StateFrame{
	JTextField jt1=new JTextField("Friendly Message");
	JButton take=new JButton("Take");
	JButton retu=new JButton("Return");
	
	protected UI2(StateManager parent) {
		super(parent);
		super.getContentPane().add(jt1);
		super.getContentPane().add(take);
		super.getContentPane().add(retu);
		take.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Please select one slot");
				
			}
			
		});
		retu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Take your time");
				
			}
			
		});
		super.setVisible(true);
	}
	
}
