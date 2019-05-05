package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class UI1 extends StateFrame {
	private JTextField jt1=new JTextField("Please enter your QM number:");
	private JTextField jt2=new JTextField();
	private JButton jb1=new JButton("SUBMIT");
	
	protected UI1(StateManager parent) {
		super(parent);
		super.setLayout(null);
		super.getContentPane().add(jt1);
//		jt1.setBounds(x, y, width, height);
		super.getContentPane().add(jt2);
		super.getContentPane().add(jb1);
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if(checkValid) 
				{
					new UI2(null);
				}
			}
			
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		new UI1(null);
	}
}
