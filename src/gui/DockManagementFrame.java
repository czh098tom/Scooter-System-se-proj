package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    JLabel notices = new JLabel("White:available      Grey:unlocked       Black:Empty");
    
    /**
     * A constructor, makes a docking GUI frame
     * Including all three docking stations, and the status of each slot
     */
    public DockManagementFrame() {
        this.setTitle("Docking Management System");
        this.setSize(700,500);
        this.setLayout(new GridLayout(5,1,0,0));

        line.setSize(700,50);
        line.setLayout(new FlowLayout(0));
        line.add(dock);
        line.add(user);

        user.addActionListener(this);

        stationA.setSize(700,200);
        stationA.setLayout(new FlowLayout(0));
        stationA.add(A);
        for(i=0;i<8;i++){
            if(array[0][i]==0){
                ImageIcon black = new ImageIcon("images\\black.png");
                JLabel b = new JLabel(black);
                stationA.add(b);
            }
            if(array[0][i]==1){
                ImageIcon white = new ImageIcon("images\\white.png");
                JLabel w = new JLabel(white);
                stationA.add(w);
            }
            if(array[0][i]==2){
                ImageIcon grey = new ImageIcon("images\\grey.png");
                JLabel g = new JLabel(grey);
                stationA.add(g);
            }
        }

        stationB.setSize(700,200);
        stationB.setLayout(new FlowLayout(0));
        stationB.add(B);
        for(i=0;i<8;i++){
            if(array[1][i]==0){
                ImageIcon black = new ImageIcon("images\\black.png");
                JLabel b = new JLabel(black);
                stationB.add(b);
            }
            if(array[1][i]==1){
                ImageIcon white = new ImageIcon("images\\white.png");
                JLabel w = new JLabel(white);
                stationB.add(w);
            }
            if(array[1][i]==2){
                ImageIcon grey = new ImageIcon("images\\grey.png");
                JLabel g = new JLabel(grey);
                stationB.add(g);
            }
        }

        stationC.setSize(700,200);
        stationC.setLayout(new FlowLayout(0));
        stationC.add(C);
        for(i=0;i<8;i++){
            if(array[2][i]==0){
                ImageIcon black = new ImageIcon("images\\black.png");
                JLabel b = new JLabel(black);
                stationC.add(b);
            }
            if(array[2][i]==1){
                ImageIcon white = new ImageIcon("images\\white.png");
                JLabel w = new JLabel(white);
                stationC.add(w);
            }
            if(array[2][i]==2){
                ImageIcon grey = new ImageIcon("images\\grey.png");
                JLabel g = new JLabel(grey);
                stationC.add(g);
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
        array = new int[][]{{0, 2, 1, 0, 0, 2, 0, 0}, // '0' : empty(black)  '1' : available(white)  '2' : unlocked(grey)
                {0, 2, 1, 2, 0, 2, 0, 2},
                {0, 0, 1, 2, 0, 0, 0, 2}};
        DockManagementFrame MF = new DockManagementFrame();
        MF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
    	//Frame swapping with Manager_User_Frame
        if(e.getSource()==user){
            setVisible(false);
            new UserManagementFrame().setVisible(true);
            dispose();
        }
    }
}