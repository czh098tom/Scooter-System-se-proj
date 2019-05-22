package gui;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Manager_User_Frame extends JFrame implements ActionListener{
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

    //JButton spot = new JButton("Spot");
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

        Object[] columnNames = new Object[]{"Name","ID","Number of fines","Using time"};
        Object[][] rowData = new Object[1][4];
        table = new JTable(rowData,columnNames);
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
    public void actionPerformed(ActionEvent e) { //Frame swapping with Manager_Dock_Frame
        if(e.getSource()==dock){
            setVisible(false);
            new Manager_Dock_Frame().setVisible(true);
            dispose();
        }
        if(e.getSource()==confirm){
            id = name_input.getText();
            String pattern = "^\\d{9}$";
            boolean ismatch = Pattern.matches(pattern,id);
            if(!ismatch) name_input.setText("Wrong format. Must be 9 digits.");
            else name_input.setText("");
        }
        if(e.getSource()==send){
            String path = "C:\\Users\\YSY\\Desktop\\Èí¼þ¹¤³Ì\\paperprotype\\a.txt";
            create_file(path);
            String content = "User name:\r\nUser ID:\r\nTotal using time:\r\nIf any fine:\r\n";
            write_file(content,path);
        }
    }

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