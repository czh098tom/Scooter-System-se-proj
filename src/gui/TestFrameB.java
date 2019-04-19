package gui;

import javax.swing.*;

public class TestFrameB extends StateFrame {
	
	public TestFrameB(StateManager parent) {
		super(parent);
		JButton a=new JButton("B");
		this.getContentPane().add(a);
		a.addActionListener(this);
		super.register(a,()->new TestFrameA(parent));
		super.registerClosing(()->new TestFrameA(parent));
		this.setTitle("TestGUIB");
	}
}
