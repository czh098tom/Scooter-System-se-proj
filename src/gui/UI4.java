package gui;

import javax.swing.JButton;
/*Return frame*/
public class UI4 extends StateFrame {
	JButton[] slot;
	public UI4(StateManager parent) {
		super(parent);
		super.setLayout(null);
		super.setResizable(false);
		super.setBounds(0, 0, 750, 500);
		
		for(int i=0;i<8;i++) {
			slot[i]=new JButton("Slot "+i);
			super.getContentPane().add(slot[i]);
			slot[i].setBounds(i*93, 200, 93, 100);
		}
		
		super.registerClosing(null);
		super.setVisible(true);
		
	}
}
