package gui;

import javax.swing.*;

public class TestFrameA extends StateFrame {
	
	public TestFrameA(StateManager parent) {
		super(parent);
		JButton a=new JButton("A");
		this.getContentPane().add(a);
		a.addActionListener(this);
		super.register(a,()->new TestFrameB(parent));
		super.registerClosing(null);
		this.setTitle("TestGUIA");
	}
}
