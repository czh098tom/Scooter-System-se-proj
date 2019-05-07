package gui;

import javax.swing.JButton;
import javax.swing.JTextField;
/*Take frame*/
public class UI3 extends StateFrame{
	JTextField timer=new JTextField("Timer");
	JButton[] slot;
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
		for(int i=0;i<8;i++) {
			slot[i]=new JButton("Slot "+i);
			super.getContentPane().add(slot[i]);
			slot[i].setBounds(i*93, 200, 93, 100);
		}
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
