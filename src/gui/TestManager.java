package gui;

import data.*;

public class TestManager extends StateManager {
	
	public static void main(String[] args) {
    	//check Email format 1@2.3
    	System.out.println(User.checkEmail("1@2.3"));//true
    	System.out.println(User.checkEmail("@2.3"));//false
    	System.out.println(User.checkEmail("1@.3"));//false
    	System.out.println(User.checkEmail("1@2."));//false
    	//check QMID format with only 9 digits
    	System.out.println(User.checkQMID("123456789"));//true
    	System.out.println(User.checkQMID("1234567890"));//false
    	System.out.println(User.checkQMID("abcdefghi"));//false
    	System.out.println(User.checkQMID("12345678"));//false
    	
    	if(User.checkEmail("1@2.3")&&User.checkQMID("123456789")) {
    		DataBase.getNew().regUser(new User("123456789","aaa","1@2.3"));
    		DataBase.getCurrent().writeToFile();
    	}
    	
    	TestManager tm=new TestManager();
    	tm.setState(new TestFrameA(tm));
	}

}