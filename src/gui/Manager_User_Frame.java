package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data.DataBase;
import data.WeeklyReportData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Another GUI for manager to manage user information, including searching user info and sending emails
 * @author YSY
 *
 */
public class Manager_User_Frame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel line = new JPanel();
    JPanel search = new JPanel();
    JPanel send_email = new JPanel();

    JButton dock = new JButton("Dock");
    JButton user = new JButton("User");
    JButton confirm = new JButton("confirm");
    JButton send = new JButton("send emails for all user");

    JTextArea name_input = new JTextArea(2,20);

    JLabel name = new JLabel("Input an ID:");

    JTable table;

    JScrollPane scrollpane = new JScrollPane();

    Font f = new Font("TimesRoman",0,20);

    String id;
	private DefaultTableModel model;

    /**
     * A constructor, makes a user management GUI
     * which can search for user data and send emails.
     */
    public Manager_User_Frame(){
        name.setFont(f);
        name_input.setFont(f);
        confirm.setFont(f);

        this.setTitle("Docking Management System");
        this.setSize(700,500);
        this.setLayout(new GridLayout(4,1));

        line.setSize(700,50);
        line.setLayout(new FlowLayout(0));
        line.add(dock);
        line.add(user);
        
        search.setBounds(50,50,700,50);
        search.setLayout(new FlowLayout(0));
        search.add(name);
        search.add(name_input);
        search.add(confirm);

        send_email.setSize(700,50);
        send_email.setLayout(new FlowLayout());
        send_email.add(send);

        //Generating a searching table for manager
        Object[] columnNames = new Object[]{"Name","ID","Number of fines","Using time"};
        Object[][] rowData = new Object[1][4];
        DefaultTableModel model = new DefaultTableModel(rowData,columnNames);
        table = new JTable(model);
        scrollpane.setBounds(50,100,700,350);
        scrollpane.setViewportView(table);
        table.setRowHeight(50);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);

        dock.addActionListener(this);
        confirm.addActionListener(this);
        send.addActionListener(this);

        this.add(line);
        this.add(search);
        this.add(scrollpane);
        this.add(send_email);
        this.setVisible(true);
    }
    public static void main(String[] args){
        Manager_User_Frame MUF = new Manager_User_Frame();
        MUF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
    	DataBase db=DataBase.getCurrent();
    	//Frame swapping with Manager_Dock_Frame
        if(e.getSource()==dock){
            setVisible(false);
            new Manager_Dock_Frame().setVisible(true);
            dispose();
        }
        //Input legality check, using regular expression, and confirm search request
        if(e.getSource()==confirm){
            id = name_input.getText();
            String pattern = "^\\d{9}$";
            boolean ismatch = Pattern.matches(pattern,id);
            if(!ismatch) name_input.setText("Wrong format. Must be 9 digits.");
            else {
            	name_input.setText("");
            	model.addRow(new Object[] {WeeklyReportData.getUserName(id),id,WeeklyReportData.getFine(id),WeeklyReportData.getUserUsage(id)});
            }
        }
        //Send emails for all users
        if(e.getSource()==send){
        	int size = WeeklyReportData.getUsersSize();
        	for(int i=0;i<size;i++) {
        		String path = "C:\\Users\\YSY\\Desktop\\Èí¼þ¹¤³Ì\\paperprotype\\"+WeeklyReportData.getUserEmail(id)+".txt";
        		String content = WeeklyReportData.toString(id);
        		create_file(path);
                write_file(content,path);
        	} 
        }
    }

    /**
     * Create a .txt file as an email
     * @param path : the directory of the file
     * @return flag : whether a .txt file is successfully created
     */
    public static boolean create_file(String path){
        boolean flag = false;
        File email = new File(path);
        if(!email.exists()){
            try{
                email.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = true;
        }
        return flag;
    }
    
    /**
     * Write information into an existing .txt file. If there is none, create one
     * @param content : the information content that is going to be written
     * @param path : the directory of the file
     * @return flag : whether the file is successfully written 
     */
    public static boolean write_file(String content, String path){
        boolean flag = false;
        File email = new File(path);
        try{
            if(!email.exists()){
                email.createNewFile();
            }
            FileWriter fw = new FileWriter(path);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
        return flag;
    }
}