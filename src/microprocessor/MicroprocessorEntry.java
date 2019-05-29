package microprocessor;

import data.*;

public class MicroprocessorEntry {
	
	private static IOManager io=IOManager.getCurrent();
	
	private static int stationID=0;
	
	private static final byte IN_CANCEL=-1;
	private static final byte IN_OK=0;
	
	private static final byte OUT_HB_AVAILABLE_OFFSET=6;
	private static final byte OUT_HB_FINE_OFFSET=5;
	private static final byte OUT_HB_TR_OFFSET=4;
	private static final byte OUT_HB_VERIFY_OFFSET=3;
	
	public static void main(String[] args) {
		String s;
		try {

			while(true) {

				s=login();
				
				System.out.println("In sys.");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			io.close();
		}
	}

	private static String login() {
		String s;
		DataBase db=DataBase.getCurrent();
		Boolean inputFinishFlag;
		Boolean inSysFlag;
		do {
			inputFinishFlag=false;
			s="";
			while(!inputFinishFlag) {
				byte b=io.getFrom();
				if(b==IN_OK) {
					inputFinishFlag=true;
				}
				else if(b==IN_CANCEL) {
					s=s.substring(0, s.length()-1);
				}
				else {
					s=s+new String(new char[] {(char)b});
				}
			}
			inSysFlag=false;
			byte inputNotifier=0;
			boolean foundSlot=false;
			if(User.checkQMID(s)&&db.userExists(s)) {
				if(!db.isUserTaking(s)) {
					if(db.isUnpaid(s)||db.isTodayUsageOverFlow(s)) {
						inputNotifier+= 1<<OUT_HB_FINE_OFFSET;
					}
					else {
						boolean[] states=db.getStationState(stationID);
						for(int i=0;i<Station.SCOOTERCOUNT;i++) {
							if(states[i]) {
								inputNotifier+=i;
								foundSlot=true;
								break;
							}
						}
						if(foundSlot) {
							inputNotifier+= 1<<OUT_HB_AVAILABLE_OFFSET;
							inSysFlag=true;
						}
					}
				}
				else {
					inputNotifier+= 1<<OUT_HB_TR_OFFSET;
					boolean[] states=db.getStationState(stationID);
					for(int i=0;i<Station.SCOOTERCOUNT;i++) {
						if(!states[i]) {
							inputNotifier+=i;
							foundSlot=true;
							break;
						}
					}
					if(foundSlot) {
						inputNotifier+= 1<<OUT_HB_AVAILABLE_OFFSET;
						inSysFlag=true;
					}
				}
			}
			else {
				inputNotifier+= 1<<OUT_HB_VERIFY_OFFSET;
			}
			io.setTo(inputNotifier);
		}while(!inSysFlag);
		return s;
	}
}
