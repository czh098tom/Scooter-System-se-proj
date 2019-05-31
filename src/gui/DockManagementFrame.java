package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import data.DataBase;

/**
 * GUI for manager to monitor current docking situation.
 * @author YSY
 *
 */
public class DockManagementFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	static int[][] array = new int[3][8];
    int i,j;

    JPanel line = new JPanel();
    JPanel stationA = new JPanel();
    JPanel stationB = new JPanel();
    JPanel stationC = new JPanel();
    JPanel notification = new JPanel();

    JButton dock = new JButton("Dock");
    
    
    JButton user = new JButton("User");
 
    JLabel A = new JLabel("Station A:                           ");
  
    JLabel B = new JLabel("Station B:                           ");
  
    JLabel C = new JLabel("Station C:                           ");
    
    JLabel notices = new JLabel("White:available            Black:occupied");
    
    /**
     * A constructor, makes a docking GUI frame
     * Including all three docking stations, and the status of each slot
     */
    public DockManagementFrame() {
    	
    	DataBase db = DataBase.getCurrent();
        this.setTitle("Docking Management System");
        this.setSize(700,500);
        this.setLayout(new GridLayout(5,1,0,0));
        
        stationA.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        stationB.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        stationC.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        A.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        B.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        C.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        dock.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        user.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        notices.setFont(new Font(Font.DIALOG,Font.BOLD,25));

        line.setSize(1000,50);
        line.setLayout(new FlowLayout(0));
        line.add(dock);
        line.add(user);

        user.addActionListener(this);

        stationA.setSize(1000,200);
        stationA.setLayout(new FlowLayout(0));
        stationA.add(A);
        
        stationB.setSize(700,200);
        stationB.setLayout(new FlowLayout(0));
        stationB.add(B);

        stationC.setSize(700,200);
        stationC.setLayout(new FlowLayout(0));
        stationC.add(C);
        
        boolean[] states=new boolean[8];
        for(i=0;i<3;i++){
            states = db.getStationState(i);
        	for(j=0;j<8;j++){
        		if(states[j]==true){
        			ImageIcon black = new ImageIcon("images\\black.png");
        			JLabel b = new JLabel(black);
        			if(i==0) stationA.add(b);
        			if(i==1) stationB.add(b);
        			if(i==2) stationC.add(b);
        		}
        		if(states[j]==false){
        			ImageIcon white = new ImageIcon("images\\white.png");
        			JLabel w = new JLabel(white);
        			if(i==0) stationA.add(w);
        			if(i==1) stationB.add(w);
        			if(i==2) stationC.add(w);
        		}
        	}
        }
		
        notification.setSize(700,50);
        notification.add(notices,BorderLayout.CENTER);

        this.add(line);
        this.add(stationA);
        this.add(stationB);
        this.add(stationC);
        this.add(notification);
        this.setResizable(false);
        this.setVisible(true);
    }
    public static void main(String[] args){
        DockManagementFrame MF = new DockManagementFrame();
        MF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
    	//Frame swapping with Manager_User_Frame
        if(e.getSource()==user){
            setVisible(false);
            UserManagementFrame umf=new UserManagementFrame();
            umf.setVisible(true);
            umf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }
}