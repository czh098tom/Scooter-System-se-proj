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
    		db.regUser(new User("111111111","AAA","1@2.3"));
    		db.regUser(new User("222222222","BBB","1@2.3"));
    		db.regUser(new User("333333333","CCC","1@2.3"));
    		for(int i=0;i<4;i++) {
        		db.putScooter(0,i,"A"+i);
    		}

    		System.out.println("1st:");
    		db.takeScooter("111111111",0,0);
    		db.returnScooter("111111111","A0",0,7);
    		
    		db.writeToFile();

    		//-2,000,000 if current overdue
    		System.out.println("2nd:");
    		db.takeScooter("222222222",0,1);

    		//-8,000,000 if today overflow
    		System.out.println("3rd:");
    		db.takeScooter("333333333",0,2);
    		db.returnScooter("333333333","A2",0,5);
    		db.takeScooter("333333333",0,5);
    		
    		db.writeToFile();
    	}
	}
}
