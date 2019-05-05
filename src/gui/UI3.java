package gui;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI3 extends StateFrame{
	JTextField jt1=new JTextField("Timer");
	JButton jb1=new JButton();
	JButton jb2=new JButton();
	JButton jb3=new JButton();
	JButton jb4=new JButton();
	JButton jb5=new JButton();
	JButton jb6=new JButton();
	JButton jb7=new JButton();
	JButton jb8=new JButton();
	JButton ok=new JButton("Ok");
	JButton cancel=new JButton("Cancel");
	
	protected UI3(StateManager parent) {
		super(parent);
		super.getContentPane().add(jt1);
		super.getContentPane().add(jb1);
		super.getContentPane().add(jb2);
		super.getContentPane().add(jb3);
		super.getContentPane().add(jb4);
		super.getContentPane().add(jb5);
		super.getContentPane().add(jb6);
		super.getContentPane().add(jb7);
		super.getContentPane().add(jb8);
		super.getContentPane().add(ok);
		super.getContentPane().add(cancel);
	}
}
