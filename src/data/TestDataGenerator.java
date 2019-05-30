package data;

public class TestDataGenerator {
	
	public static void main(String[] args) {
//		/*
//    	//check Email format 1@2.3
//    	System.out.println(User.checkEmail("1@2.3"));//true
//    	System.out.println(User.checkEmail("@2.3"));//false
//    	System.out.println(User.checkEmail("1@.3"));//false
//    	System.out.println(User.checkEmail("1@2."));//false
//    	//check QMID format with only 9 digits
//    	System.out.println(User.checkQMID("123456789"));//true
//    	System.out.println(User.checkQMID("1234567890"));//false
//    	System.out.println(User.checkQMID("abcdefghi"));//false
//    	System.out.println(User.checkQMID("12345678"));//false
//    	*/
    	if(User.checkEmail("1@2.3")&&User.checkQMID("123456789")) {
    		//DataBase db=DataBase.getCurrent();
    		DataBase db=DataBase.getNew();
    		db.initialize();
    		new RegistrationContract("111111111","AAA","1@2.3").register();
    		new RegistrationContract("222222222","BBB","2@2.3").register();
    		new RegistrationContract("333333333","CCC","3@2.3").register();
    		for(int i=0;i<4;i++) {
        		db.putScooter(0,i,"A"+i);
    		}

    		System.out.println("1st:");
    		new TakeContract("111111111",0,0).takeScooter();
    		new ReturnContract("111111111",0,7).returnScooter();
    		
    		db.writeToFile();

    		//-2,000,000 if current overdue
    		System.out.println("2nd:");
    		new TakeContract("222222222",0,1).takeScooter();

    		//-8,000,000 if today overflow
    		System.out.println("3rd:");
    		new TakeContract("333333333",0,2).takeScooter();
    		new ReturnContract("333333333",0,5).returnScooter();
    		new TakeContract("333333333",0,5).takeScooter();
    		
    		db.writeToFile();
    	}
	}
}
