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
		super.setResizable(false);
		super.setLayout(null);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(jt1);
		jt1.setEditable(false);
		jt1.setBounds(0, 0, 750, 200);
		super.getContentPane().add(take);
		take.setBounds(50, 250, 200, 100);
		take.addActionListener(this);
		super.getContentPane().add(retu);
		retu.setBounds(500, 250, 200, 100);
		retu.addActionListener(this);
		super.register(take, ()->new UI3(parent));
		super.register(retu, ()->new UI3(parent));
		super.registerClosing(null);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new UI2(null);
	}
	
}
