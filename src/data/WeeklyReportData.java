package data;

import java.util.LinkedList;

public class WeeklyReportData {
	/**Author : Zihao Chen & YSY
	 * Store user ID and name in report.
	 */
	
	static String name;
	static String id;
	static String email;
	static long usage;
	static int fine;
	
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
	public WeeklyReportData(String name, String id, long usage, int fine,String email) {
		this.name = name;
		this.id = id;
		this.usage = usage;
		this.fine = fine;
		this.email = email;
	}

	/**
	 * Generate necessary information for sending emails.
	 * @param id : a given user's id
	 * @return it will return several lines of user information (String)
	 */
	public static String toString(int i) {
		DataBase db = DataBase.getCurrent();
		LinkedList<User> users = db.getUsers();
		return "User name:"+users.get(i).getName()+"\\r\\nUser ID:"+users.get(i).getId()+"\\r\\nTotal using time:"+db.sumWeeklyUsage(id)+"\\r\\nIf any fine:"+db.sumFine(id)+"\\r\\n";
	}
	
	/**
	 * Generate an object list for JTable
	 * @return return an object list of weeklyreportdata
	 */
	public static Object[] toObjList() {
		return new Object[] {name,id,usage,fine};
	}
	
	/**
	 * Get the i-th user's email address
	 * @param i
	 * @return
	 */
	public static String getEmail(int i) {
		DataBase db = DataBase.getCurrent();
		LinkedList<User> users = db.getUsers();
		return users.get(i).getEmail();
	}
}
