package gui;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI3 extends StateFrame{
	JTextField timer=new JTextField("Timer");
	JButton slot1=new JButton("Slot 1");
	JButton slot2=new JButton("Slot 2");
	JButton slot3=new JButton("Slot 3");
	JButton slot4=new JButton("Slot 4");
	JButton slot5=new JButton("Slot 5");
	JButton slot6=new JButton("Slot 6");
	JButton slot7=new JButton("Slot 7");
	JButton slot8=new JButton("Slot 8");
	JButton ok=new JButton("Ok");
	JButton cancel=new JButton("Cancel");
	
	protected UI3(StateManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		super.getContentPane().add(timer);
		timer.setBounds(0, 0, 750, 200);
		timer.setEditable(false);
		super.getContentPane().add(slot1);
		slot1.setBounds(0, 200, 93, 100);
		super.getContentPane().add(slot2);
		slot2.setBounds(93, 200, 93, 100);
		super.getContentPane().add(slot3);
		slot3.setBounds(186, 200, 93, 100);
		super.getContentPane().add(slot4);
		slot4.setBounds(279, 200, 93, 100);
		super.getContentPane().add(slot5);
		slot5.setBounds(372, 200, 93, 100);
		super.getContentPane().add(slot6);
		slot6.setBounds(465, 200, 93, 100);
		super.getContentPane().add(slot7);
		slot7.setBounds(558, 200, 93, 100);
		super.getContentPane().add(slot8);
		slot8.setBounds(651, 200, 93, 100);
		super.getContentPane().add(ok);
		ok.setBounds(50, 350, 200, 100);
		ok.addActionListener(this);
		super.getContentPane().add(cancel);
		cancel.setBounds(500, 350, 200, 100);
		cancel.addActionListener(this);
		super.register(ok, null);
		super.register(cancel, null);
		super.registerClosing(null);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new UI3(null);
	}
}
