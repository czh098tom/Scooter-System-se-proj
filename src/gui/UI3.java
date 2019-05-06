package gui;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI3 extends StateFrame{
	JTextField jt1=new JTextField("Timer");
	JButton slot1=new JButton();
	JButton slot2=new JButton();
	JButton slot3=new JButton();
	JButton slot4=new JButton();
	JButton slot5=new JButton();
	JButton slot6=new JButton();
	JButton slot7=new JButton();
	JButton slot8=new JButton();
	JButton ok=new JButton("Ok");
	JButton cancel=new JButton("Cancel");
	
	protected UI3(StateManager parent) {
		super(parent);
		super.getContentPane().add(jt1);
		super.getContentPane().add(slot1);
		super.getContentPane().add(slot2);
		super.getContentPane().add(slot3);
		super.getContentPane().add(slot4);
		super.getContentPane().add(slot5);
		super.getContentPane().add(slot6);
		super.getContentPane().add(slot7);
		super.getContentPane().add(slot8);
		super.getContentPane().add(ok);
		super.getContentPane().add(cancel);
	}
}
