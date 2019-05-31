package data;

public class TestDataGenerator {
	
	public static void main(String[] args) {
		DataBase db=DataBase.getNew();
		db.initialize();
		new RegistrationContract("111111111","AAA","1@2.3").register();
		new RegistrationContract("222222222","BBB","2@2.3").register();
		new RegistrationContract("333333333","CCC","3@2.3").register();
		
		String[] stationIDs=new String[]{"A","B","C"};
		
		for(int j=0;j<3;j++) {
    		for(int i=0;i<4;i++) {
        		db.putScooter(0,i,stationIDs[j]+i);
    		}
		}

		new TakeContract("111111111",0,0).takeScooter();
		new ReturnContract("111111111",1,7).returnScooter();

		//-2,000,000 if current overdue
		new TakeContract("222222222",1,1).takeScooter();

		//-8,000,000 if today overflow
		new TakeContract("333333333",2,2).takeScooter();
		new ReturnContract("333333333",0,5).returnScooter();
		new TakeContract("333333333",0,5).takeScooter();
	}
}
