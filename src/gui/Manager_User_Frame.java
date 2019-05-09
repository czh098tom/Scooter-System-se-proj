package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Manager_User_Frame extends JFrame implements ActionListener{
    JPanel line = new JPanel();
    JPanel search = new JPanel();

    JButton dock = new JButton("Dock");
    JButton user = new JButton("User");
    JButton confirm = new JButton("confirm");

    JTextField name_input = new JTextField(20);

    JLabel name = new JLabel("Input a name:");

    //JButton spot = new JButton("Spot");
    public Manager_User_Frame(){
        this.setTitle("Docking Management System");
        this.setSize(700,500);
        this.setLayout(new GridLayout(3,1));

        line.setSize(700,50);
        line.setLayout(new FlowLayout(0));
        line.add(dock);
        line.add(user);
        //line.add(spot);

        //search.setSize(700,50);
        search.setBounds(50,50,700,50);
        search.setLayout(new FlowLayout(0));
        search.add(name);
        search.add(name_input);
        search.add(confirm);
        dock.addActionListener(this);

        this.add(line);
        this.add(search);
        this.setVisible(true);
    }
    public static void main(String[] args){
        Manager_User_Frame MUF = new Manager_User_Frame();
        MUF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Frame swapping with Manager_Dock_Frame
        if(e.getSource()==dock){
            setVisible(false);
            new Manager_Dock_Frame().setVisible(true);
            dispose();
        }
    }
}
