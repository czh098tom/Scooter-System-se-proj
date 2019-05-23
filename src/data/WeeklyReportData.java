package data;

import java.util.LinkedList;

public class WeeklyReportData {
	/**Author : Zihao Chen & YSY
	 * Store user ID and name in report.
	 */
	
	String name;
	String id;
	long sumWeeklyUsage;
	int sumFine;
	
	/**
	 * An empty constructor
	 */
	public WeeklyReportData() {
		
	}
	
	/**
	 * A constructor.
	 * @param name
	 * @param id
	 * @param sumWeeklyUsage
	 * @param sumFine
	 */
	public WeeklyReportData(String name, String id, long sumWeeklyUsage, int sumFine) {
		this.name = name;
		this.id = id;
		this.sumWeeklyUsage = sumWeeklyUsage;
		this.sumFine = sumFine;
	}

	/**
	 * Generate necessary information for sending emails.
	 * @param id : a given user's id
	 * @return it will return several lines of user information (String)
	 */
	public static String toString(String id) {
		return "User name:"+WeeklyReportData.getUserName(id)+"\\r\\nUser ID:"+id+"\\r\\nTotal using time:"+WeeklyReportData.getUserUsage(id)+"\\r\\nIf any fine:"+WeeklyReportData.getFine(id)+"\\r\\n";
	}
	
	/**
	 * Get the user's name with given userid
	 * @param id : a given user's id
	 * @return it returns a name (String)
	 */
	public static String getUserName(String id) {
		DataBase db=DataBase.getCurrent();
		User user = new User();
    	user = db.getUserByID(id);
    	return user.getName();
    }
	
	/**
	 * Get the user's total using time in minutes with a given userid 
	 * @param id : a given user's id
	 * @return it returns a time value (long)
	 */
    public static long getUserUsage(String id) {
    	DataBase db=DataBase.getCurrent();
    	return db.sumWeeklyUsage(id);
    }
    
    /**
     * Get the email address with a given user id
     * @param id : a given user id
     * @return it returns a email address (String)
     */
    public static String getUserEmail(String id) {
    	DataBase db=DataBase.getCurrent();
    	User user = new User();
    	user = db.getUserByID(id);
    	return user.getEmail();
    }
    
    /**
     * Get the number of total users
     * @return it returns an integer (int)
     */
    public static int getUsersSize() {
    	DataBase db=DataBase.getCurrent();
    	LinkedList<User> users = db.getUsers();
    	return users.size();
    }
    
    /**
     * Get the fine of a user
     * @param id : a given user id
     * @return it returns an integer (int)
     */
    public static int getFine(String id) {
    	DataBase db=DataBase.getCurrent();
    	return db.sumFine(id);
    }
}
