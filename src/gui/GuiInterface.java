package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;
public class GuiInterface extends JFrame implements ActionListener{
	
// Define GUI component names and variable names.
	 
	  JButton choiceA,choiceB,choiceC,choiceD,choiceE,choiceF,choiceG,choiceH,
	          choiceI,choiceJ,choiceK,choiceL,choiceM,choiceN;
      JButton pressButton=null;
	  JButton clearButton=null;
	  JPanel panel=null;
	  static Connection conn; 
	  JScrollPane result=null;
	  JLabel warnPane,allChoice=null; 
	  JTextField userText=null ;
	  String choices="<html>(a) Present a report listing the Manager¡¯s name and telephone number for each hall of residence. <br/>" + 
	  		"(b) Present a report listing the names and student numbers of students with the details of their lease agreements. <br/>" + 
	  		"(c) Display the details of lease agreements that include the Summer Semester. <br/>" + 
	  		"(d) Display the details of the total rent paid by a given student. <br/>" + 
	  		"(e) Present a report on students that have not paid their invoices by a given date. <br/>" + 
	  		"(f) Display the details of flat inspections where the property was found to be in an unsatisfactory condition. <br/>" + 
	  		"(g) Present a report of the names and student numbers of students with their room number and place number in a particular hall of residence.<br/>" + 
	  		"(h) Present a report listing the details of all students currently on the waiting list for accommodation, that is, not placed. <br/>" + 
	  		"(i) Display the total number of students in each student category. <br/>" + 
	  		"(j) Present a report of the names and student numbers for all students who have not supplied details of their next-of-kin. <br/>" + 
	  		"(k) Display the name and internal telephone number of the Advisor of Studies for a particular student. <br/>" + 
	  		"(l) Display the minimum, maximum, and average monthly rent for rooms in halls of residence.<br/>" + 
	  		"(m) Display the total number of places in each hall of residence.<br/>" + 
	  		"(n) Display the staff number, name, age, and current location of all members of the accommodation staff who are over 60 years old today. ";
	JTable tabDemo;
	JTableHeader jth;
	int testNum=0;
	  
//Construction method
	
	  public GuiInterface(String name) {
		  super(name);
		  
//Creating components
                                  panel = new JPanel();
		  panel.setLayout(null);
		  
	       allChoice = new JLabel();
	       allChoice.setBounds(10,0,900,530);
	       allChoice.setText(choices);
	       allChoice.setFont(new Font("Symbol",Font.BOLD,19));
	       panel.add(allChoice);
	       
	       warnPane = new JLabel("<html>Welcome to the accommodation inquiry system. "
	       		+ "You can select the content you want to inquire and press the corresponding button.");
	       warnPane.setBounds(910,270,500,110);
	       warnPane.setFont(new Font("",Font.BOLD,20));
	       warnPane.setForeground(Color.BLUE);
	       panel.add(warnPane);
	       
	       result = new JScrollPane();	       
	       result.setBounds(10,530,1390,300);
	       panel.add(result);

	       userText = new JTextField(20);
	       userText.setBounds(900,380,400,70);
	       panel.add(userText);
	       
	       pressButton = new JButton("Press for result");
		   pressButton.addActionListener(this);
		   pressButton.setBounds(900,450,500,70);
		   panel.add(pressButton);
		   
		   clearButton=new JButton("C");
	       clearButton.setBounds(1300,380,100,70);
	       clearButton.addActionListener(this);
	       panel.add(clearButton);
	       
	          choiceA=new JButton("a");
	          choiceA.setBounds(900,0,100,90);
	    	  panel.add(choiceA);
	    	  choiceB=new JButton("b");
	    	  choiceB.setBounds(1000,0,100,90);
	    	  panel.add(choiceB);
	    	  choiceC=new JButton("c");
	    	  choiceC.setBounds(1100,0,100,90);
	    	  panel.add(choiceC);
	    	  choiceD=new JButton("d");
	    	  choiceD.setBounds(1200,0,100,90);
	    	  panel.add(choiceD);
	    	  choiceE=new JButton("e");
	    	  choiceE.setBounds(1300,0,100,90);
	    	  panel.add(choiceE);
	    	  choiceF=new JButton("f");
	    	  choiceF.setBounds(900,90,100,90);
	    	  panel.add(choiceF);
	    	  choiceG=new JButton("g");
	    	  choiceG.setBounds(1000,90,100,90);
	    	  panel.add(choiceG);
	    	  choiceH=new JButton("h");
	    	  choiceH.setBounds(1100,90,100,90);
	    	  panel.add(choiceH);
	    	  choiceI=new JButton("i");
	    	  choiceI.setBounds(1200,90,100,90);
	    	  panel.add(choiceI);
	    	  choiceJ=new JButton("j");
	    	  choiceJ.setBounds(1300,90,100,90);
	    	  panel.add(choiceJ);
	    	  choiceK=new JButton("k");
	          choiceK.setBounds(900,180,100,90);
	    	  panel.add(choiceK);
	    	  choiceL=new JButton("l");
	          choiceL.setBounds(1000,180,100,90);
	    	  panel.add(choiceL);
	    	  choiceM=new JButton("m");
	          choiceM.setBounds(1100,180,100,90);
	    	  panel.add(choiceM);
	    	  choiceN=new JButton("n");
	          choiceN.setBounds(1200,180,100,90);
	    	  panel.add(choiceN);
	    	  
//Register the monitor for the button
	    	  choiceA.addActionListener(this);
	    	  choiceB.addActionListener(this);
	    	  choiceC.addActionListener(this);
	    	  choiceD.addActionListener(this);
	    	  choiceE.addActionListener(this);
	    	  choiceF.addActionListener(this);
	    	  choiceG.addActionListener(this);
	    	  choiceH.addActionListener(this);
	    	  choiceI.addActionListener(this);
	    	  choiceJ.addActionListener(this);	    	  
	    	  choiceK.addActionListener(this);
	    	  choiceL.addActionListener(this);
	    	  choiceM.addActionListener(this);	    	  
	    	  choiceN.addActionListener(this);
	    	  
	    	  choiceA.setFont(new Font("Georgia",0,20));
	    	  choiceB.setFont(new Font("Georgia",0,20));
	    	  choiceC.setFont(new Font("Georgia",0,20));
	    	  choiceD.setFont(new Font("Georgia",0,20));
	    	  choiceE.setFont(new Font("Georgia",0,20));
	    	  choiceF.setFont(new Font("Georgia",0,20));
	    	  choiceG.setFont(new Font("Georgia",0,20));
	    	  choiceH.setFont(new Font("Georgia",0,20));
	    	  choiceI.setFont(new Font("Georgia",0,20));
	    	  choiceJ.setFont(new Font("Georgia",0,20));
	    	  choiceK.setFont(new Font("Georgia",0,20));
	    	  choiceL.setFont(new Font("Georgia",0,20));
	    	  choiceM.setFont(new Font("Georgia",0,20));
	    	  choiceN.setFont(new Font("Georgia",0,20));
	    	  pressButton.setFont(new Font("Georgia",0,20));
	    	  clearButton.setFont(new Font("Georgia",0,20));

//Definition of Relevant Properties of Window
	       this.setSize(1430,900);
	       this.setLocation(10, 10);
	       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   this.add(panel);
		   this.setVisible(true);
	  }
/*
 * main method
 * Specific Processing Method for Connecting Data Sources and Displaying Data
 * Create a GuiInterface object.	  
 */
	  public static void main(String[] args) throws SQLException {  
		  
		  String driverName="com.mysql.jdbc.Driver";  
		  String dbURL="jdbc:mysql://localhost:3306/UniversityAccomodationOffice?useSSL=true";    
		  String userName="root";   
		  String userPwd="********";
		  try{
         
		//Get connected
		  Class.forName(driverName);
		  conn = DriverManager.getConnection(dbURL,userName,userPwd);
		  }catch(ClassNotFoundException cnfe){
	          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
		  }
		  GuiInterface frame = new GuiInterface("Accommodation Inquiry System");
		  //conn.close();
	  }
//		 Listen to choiceA...choiceN, pressButton and clearButton
	  public void actionPerformed(ActionEvent e) {
				 if(e.getSource()==choiceA) {
					 warnPane.setText("<html>The following table shows what you are looking for");
				   try {
					   
					// Building query conditions
					   String sql = "select managerName,telephone from residencehall";  
					 PreparedStatement pstm = conn.prepareStatement(sql);
					 
					// Execution query
					 ResultSet rs = pstm.executeQuery();
					 
					// Calculate how many records are there
					 int count = 0;
					 while(rs.next()){
					    count++;
					 }
					 rs = pstm.executeQuery();
					 
					// Convert the recorded data obtained by the query 
					//into a data form suitable for generating JTable
				       Object[][] info = new Object[count][2];
				       count = 0;
				       while(rs.next()){
				         info[count][0] = rs.getString("telephone");
				         info[count][1] = rs.getString("managerName");        
				         count++;
				       }
				       
				    // Define header of table
				       String[] title = {"telephone","managerName"};
				       
				    // Create JTable
					 tabDemo = new JTable(info,title);
									 
					// Display header of table
					 jth = tabDemo.getTableHeader();
					 
					// Add JTable to the JScrollPane result 
					 result.getViewport().add(tabDemo); 
					 //result.
					// rs.close();
					// pstm.close();
					 
				   }catch(SQLException sqle){
				          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
			        }
				 }
				 else if(e.getSource()==choiceB) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select s.sID,s.sName,l.pNo,l.lNo,l.rentalPeriod,l.startTime,l.endTime\r\n" + 
						 		"from student s,leases l\r\n" + 
						 		"where l.sID=s.sID";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][7];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf(rs.getInt("sID"));
					         info[count][1] = rs.getString("sName");  
					         info[count][2] = Integer.valueOf(rs.getInt("pNo")); 
					         info[count][3] = Integer.valueOf(rs.getInt("lNo"));
					         info[count][4] = rs.getString("rentalperiod"); 
					         info[count][5] = rs.getDate("startTime").toString();
					         info[count][6] = rs.getDate("endTime").toString();
					         count++;
					       }
					       String[] title = {"sID","sName","pNo","lNo","rentalPeriod","startTime","endTime"};
						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
					    
				 }
				 else if(e.getSource()==choiceC) {
					 warnPane.setText("<html>The following table shows what you are looking for");
                     try {
						 String sql = "select l.sID,l.pNo,l.lNo,l.rentalPeriod,l.startTime,l.endTime\r\n" + 
						 		"from invoices i,leases l\r\n" + 
						 		"where i.lNo=l.lNo and i.semester='summer semester'";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][6];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf(rs.getInt("sID"));
					     
					         info[count][1] = Integer.valueOf(rs.getInt("pNo")); 
					         info[count][2] = Integer.valueOf(rs.getInt("lNo"));
					         info[count][3] = rs.getString("rentalperiod"); 
					         info[count][4] = rs.getDate("startTime").toString();
					         info[count][5] = rs.getDate("endTime").toString();
					         count++;
					       }
					       String[] title = {"sID","pNo","lNo","rentalPeriod","startTime","endTime"}; 

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceD) {
			    	   warnPane.setText("Please enter the student ID you need to query:");
			    	   testNum=2;
				 }
				 else if(e.getSource()==choiceE) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select sID,payDue,payDate from invoices inner join"
						 		+ " payment on invoices.payID=payment.payID where payDue<payDate "; 
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][3];
					       count = 0;
					       while(rs.next()){
					         
					         info[count][0] = Integer.valueOf(rs.getInt("sID"));
					         info[count][1] = rs.getDate("payDue").toString(); 
					         info[count][2] = rs.getDate("payDate").toString(); 
					                
					         count++;
					       }
					       String[] title = {"sID","payDue","payDate"};

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceF) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select * from inspection where satisfactoryCondition='N'";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][6];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf(rs.getInt("staffNo"));    
					         info[count][1] = Integer.valueOf(rs.getInt("fID"));   
					         info[count][2] = rs.getDate("date").toString();
					         info[count][3] = rs.getString("staffName");
					         info[count][4] = rs.getString("satisfactoryCondition");
					         info[count][5] = rs.getString("additionalComments");
					         count++;
					       }
					       String[] title = {"staffNo","fID","date","staffName","satisfactoryCondition","additionalComments"}; 
					       

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceG) {
					 warnPane.setText("Please enter the hall ID you need to query");
					 testNum=1;
					 
				 }
				 else if(e.getSource()==choiceH) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "/*h*/select sID,sName,sStreet,sCity,sPostcode,birthDate,sSex,category,nationality,smoker,\r\n" + 
						 		"specialNeed,additionalComments,course,courseNo, aID\r\n" + 
						 		"from student\r\n" + 
						 		"where currentStatus='waiting'"; 
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][15];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf( rs.getInt("sID"));
					         info[count][1] = rs.getString("sName");
					         info[count][2] = rs.getString("sStreet");
					         info[count][3] = rs.getString("sCity");        
					         info[count][4] = Integer.valueOf( rs.getInt("sPostcode") );
					         info[count][5] = rs.getDate("birthDate").toString();
					         info[count][6] = rs.getString("sSex"); 
					         info[count][7] = rs.getString("category"); 
					         info[count][8] = rs.getString("nationality"); 
					         info[count][9] = rs.getString("smoker"); 
					         info[count][10] = rs.getString("specialNeed"); 
					         info[count][11] = rs.getString("additionalComments"); 				
					         info[count][12] = rs.getString("course"); 
					         info[count][13] = Integer.valueOf( rs.getInt("aID"));
					         info[count][14] = Integer.valueOf( rs.getInt("courseNo"));         
					         count++;
					       }
					       String[] title = {"sID","sName","sStreet","sCity","sPostcode","birthDate","sSex","category","nationality","smoker","specialNeed","additionalComments","course","aID","courseNo"};
					 

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceI) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						  String sql = "select count(*),category from student group by category"; 
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][2];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf( rs.getInt("count(*)"));
					         info[count][1] = rs.getString("category");        
					         count++;
					       }
					       String[] title = {"NoOfCategory","category"};

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceJ) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select sID,sName from student where sID not in(select sID from nextofkin)"; 
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
					       Object[][] info = new Object[count][2];
					       count = 0;
					       while(rs.next()){
					         info[count][0] = Integer.valueOf( rs.getInt("sID"));
					         info[count][1] = rs.getString("sName");        
					         count++;
					       }
					       String[] title = {"sID","sName"};
						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo);
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceK) {
					 warnPane.setText("Please enter the student ID you need to query: ");
					 testNum=3;
				 }
				 else if(e.getSource()==choiceL) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select min(MonthRate) , max(MonthRate) , avg(MonthRate) from room where AccommodationType='Hall'";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
						 Object[][] info = new Object[count][3];
						 count = 0;
						 while(rs.next()){
						   info[count][0] = Integer.valueOf( rs.getInt("min(MonthRate)"));  
						   info[count][1] = Integer.valueOf( rs.getInt("max(MonthRate)")); 
						   info[count][2] = Integer.valueOf( rs.getInt("avg(MonthRate)"));   
						   count++;
						 }
						 String[] title = {"minMonthRate","maxMonthRate","avg(MonthRate)"};

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceM) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select hID, count(*) from room where hID is NOT NULL group by hID";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
						 Object[][] info = new Object[count][2];
						 count = 0;
						 while(rs.next()){
						   info[count][0] = Integer.valueOf( rs.getInt("hID"));
						   info[count][1] = Integer.valueOf( rs.getInt("count(*)"));       
						   count++;
						 }
						 String[] title = {"hID","noOfpalce"};

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if(e.getSource()==choiceN) {
					 warnPane.setText("<html>The following table shows what you are looking for");

					 try {
						 String sql = "select staffNo,staffName,(118-date_format(staffBirth,'%y')) as age, location from accommodationstaff where (118-date_format(staffBirth,'%y')) >60";
						 PreparedStatement pstm = conn.prepareStatement(sql);
						 ResultSet rs = pstm.executeQuery();
						 int count = 0;
						 while(rs.next()){
						    count++;
						 }
						 rs = pstm.executeQuery();
						 Object[][] info = new Object[count][4];
						 count = 0;
						 while(rs.next()){
						   info[count][0] = Integer.valueOf( rs.getInt("staffNo"));  
						   info[count][1] = rs.getString("staffName");
						   info[count][2] = Integer.valueOf( rs.getInt("age"));
						   info[count][3] = rs.getString("location");  
						   count++;
						 }
						 String[] title = {"staffNo","staffName","age","location"};

						 tabDemo = new JTable(info,title);
						 jth = tabDemo.getTableHeader();
						 result.getViewport().add(tabDemo); 
						 rs.close();
						 pstm.close();
					   }catch(SQLException sqle){
					          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
				        }
				 }
				 else if (e.getSource()==clearButton) {
					 userText.setText("");
				 }
				 else if (e.getSource()==pressButton) {
					 
				//Get the content in userText
					 int userInput=Integer.parseInt(userText.getText());
					 
				//If the user clicks the d button, testNum = 2, implement whichStu method	 
					 if(testNum==2)
					    whichStu(userInput);
					 
				//If the user clicks the g button, testNum = 1,	implement whichHall method
					 else if(testNum==1)
						 whichHall(userInput);
					 
				//If the user clicks the k button, testNum = 3,	implement whichAdv method
					 else if(testNum==3)
							whichAdv(userInput);
					 else
						 warnPane.setText("There is something wrong with your operation."
						 		+ " Please click the button that you need to inquire about the content first.");
					 
					 
			     }
		}
	  /*
	   * whichStu method
	   * This method is executed when the user clicks the d button 
	   * and enters the sID of the query in userText
	   */
	  
	  public void whichStu(int userInput) {
		  try {
				 String sql = "/*d*/select sID,sName,sum(payNum)\r\n" + 
				 		"from invoices\r\n" + 
				 		"where sID="+userInput; 
				 PreparedStatement pstm = conn.prepareStatement(sql);
				 ResultSet rs = pstm.executeQuery();
				 int count = 0;
				 while(rs.next()){
				    count++;
				 }
				 rs = pstm.executeQuery();
			       Object[][] info = new Object[count][3];
			       count = 0;
			       while(rs.next()){
			    	   info[count][0] = Integer.valueOf(rs.getInt("sID"));
			         info[count][1] = rs.getString("sName"); 
			         info[count][2] = Integer.valueOf(rs.getInt("sum(payNum)"));
			                
			         count++;
			       }
			       System.out.print(info[0][0]);
			       Object hh=Integer.valueOf(0);
			       
			     //Determine whether the query result is null
			       if(info[0][0]==hh) {
			    	   warnPane.setText("The ID you entered does not exist. Please re-enter it: ");			
			       }
			       else {
			    	   String[] title = {"sID","sName","totalPay"};

				       tabDemo = new JTable(info,title);
				       jth = tabDemo.getTableHeader();
				       result.getViewport().add(tabDemo); 
				       rs.close();
					   pstm.close();
			       }
			       
			   }catch(SQLException sqle){
			          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
		        }

		  
	  }
	  /*
	   * whichStu method
	   * This method is executed when the user clicks the g button 
	   * and enters the hID of the query in userText
	   */
	  public void whichHall(int userInput) {
		  try {
				 String sql = "select s.sName,l.pNo,r.rNo\r\n" + 
					  		"from student s,leases l,room r \r\n" + 
					  		"where s.sID=l.sID and r.pNo=l.pNo and r.hID="+userInput; 
				 PreparedStatement pstm = conn.prepareStatement(sql);
				 ResultSet rs = pstm.executeQuery();
				 int count = 0;
				 while(rs.next()){
				    count++;
				 }
				 rs = pstm.executeQuery();
			       Object[][] info = new Object[count][3];
			       count = 0;
			       while(rs.next()){
			    	   info[count][0] = rs.getString("sName"); 
			    	 info[count][1] = Integer.valueOf(rs.getInt("pNo")); 			         			         
			         info[count][2] = Integer.valueOf(rs.getInt("rNo"));               
			         count++;
			       }
			       
			     //Determine whether the query result is null  
			       if(count==0) {
			    	   warnPane.setText("The ID you entered does not exist. Please re-enter it: ");			
			       }
			       else {
			    	   String[] title = {"sName","pNo","rNo"};

				       tabDemo = new JTable(info,title);
				       jth = tabDemo.getTableHeader();
				       result.getViewport().add(tabDemo); 
				       rs.close();
					   pstm.close();
			       }
		       
			   }catch(SQLException sqle){
			          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
		        }		  
		  
	  }
	  
	  /*
	   * whichStu method
	   * This method is executed when the user clicks the k button 
	   * and enters the sID of the query in userText
	   */
	  public void whichAdv(int userInput) {
		  try {
			  String sql = "select aName,telephone from advisor\r\n" + 
			  		"where aID in(select aID from student where sID="+userInput+")";  
				 PreparedStatement pstm = conn.prepareStatement(sql);
				 ResultSet rs = pstm.executeQuery();
				 int count = 0;
				 while(rs.next()){
				    count++;
				 }
				 rs = pstm.executeQuery();
				 Object[][] info = new Object[count][2];
				 count = 0;
				 while(rs.next()){
				   info[count][0] = rs.getString("aName");
				   info[count][1] = rs.getString("telephone");        
				   count++;
				 }
				//Determine whether the query result is null
			       if(count==0) {
			    	   warnPane.setText("The ID you entered does not exist. Please re-enter it:");
			       }
			       else {
						 String[] title = {"aName","telephone"};

				       tabDemo = new JTable(info,title);
				       jth = tabDemo.getTableHeader();
				       result.getViewport().add(tabDemo); 
				       rs.close();
					   pstm.close();
			       }
			       
			   }catch(SQLException sqle){
			          JOptionPane.showMessageDialog(null,"Data Source Error","Error",JOptionPane.ERROR_MESSAGE);
		        }

		  
	  }
	  

}
