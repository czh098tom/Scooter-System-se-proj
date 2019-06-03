package microprocessor;

import data.*;

public class MicroprocessorEntry {
	
	private static IOManager io=IOManager.getCurrent();
	
	private static int stationID=0;
	
	private static final byte IN_CANCEL=-1;
	private static final byte IN_OK=0;
	
	private static final byte OUT_HB_SLOT_OFFSET=6;
	private static final byte OUT_HB_STATE_OFFSET=4;
	
	public static void main(String[] args) {
		LoginInfo li;
		try {

			while(true) {

				li=login();
				
				System.out.println("In sys.\r\n"+li);
				
				byte b=1;
				while(b!=IN_OK&&b!=IN_CANCEL) {
					b=io.getFrom();
				}
				if(b==IN_OK) {
					if(li.isToTake()) {
						new TakeContract(li.getUserID(),stationID,li.getTargetSlot()).takeScooter();;
					}
					else {
						new ReturnContract(li.getUserID(),stationID,li.getTargetSlot()).returnScooter();;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			io.close();
		}
	}

	private static LoginInfo login() {
		String uid;
		boolean totake=false;
		int sid=-1;
		DataBase db=DataBase.getCurrent();
		Boolean inputFinishFlag;
		Boolean inSysFlag;
		do {
			inputFinishFlag=false;
			uid="";
			while(!inputFinishFlag) {
				byte b=io.getFrom();
				if(b==IN_OK) {
					inputFinishFlag=true;
				}
				else if(b==IN_CANCEL) {
					uid=uid.substring(0, uid.length()-1);
				}
				else {
					uid=uid+new String(new char[] {(char)b});
				}
			}
			inSysFlag=false;
			byte inputNotifier=0;
			boolean foundSlot=false;
			if(User.checkQMID(uid)&&db.userExists(uid)) {
				if(!db.isUserTaking(uid)) {
					totake=true;
					if(db.isUnpaid(uid)||db.isTodayUsageOverFlow(uid)) {
						inputNotifier+= 3<<OUT_HB_STATE_OFFSET;
					}
					else {
						inputNotifier+= 1<<OUT_HB_STATE_OFFSET;
						boolean[] states=db.getStationState(stationID);
						for(int i=0;i<Station.SCOOTERCOUNT;i++) {
							if(states[i]) {
								inputNotifier+=i;
								sid=i;
								foundSlot=true;
								break;
							}
						}
						if(!foundSlot) {
							inputNotifier+= 1<<OUT_HB_SLOT_OFFSET;
						}
						else {
							inSysFlag=true;
						}
					}
				}
				else {
					totake=false;
					inputNotifier+= 2<<OUT_HB_STATE_OFFSET;
					boolean[] states=db.getStationState(stationID);
					for(int i=0;i<Station.SCOOTERCOUNT;i++) {
						if(!states[i]) {
							inputNotifier+=i;
							sid=i;
							foundSlot=true;
							break;
						}
					}
					if(!foundSlot) {
						inputNotifier+= 1<<OUT_HB_SLOT_OFFSET;
					}
					else {
						inSysFlag=true;
					}
				}
			}
			io.setTo(inputNotifier);
		}while(!inSysFlag);
		return new LoginInfo(uid,totake,sid);
	}
}
