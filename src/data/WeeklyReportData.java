package data;

import java.util.LinkedList;

public class WeeklyReportData {
	/**Author : Zihao Chen & YSY
	 * Store user ID and name in report.
	 */
	
	String name;
	String id;
	String email;
	long usage;
	int fine;
	
	public String getEmail() {
		return email;
	}
	
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
	 * Generate necessary information for sending email.
	 * @param id : a given user's id
	 * @return it will return several lines of user information (String)
	 */
	public String toString() {
		return "User name:"+name+"\\r\\nUser ID:"
				+id+"\\r\\nTotal using time:"
				+usage+"\\r\\nIf any fine:"+fine+"\\r\\n";
	}
	
	/**
	 * Generate an object list for JTable
	 * @return return an object list of {@link WeeklyReportData}
	 */
	public Object[] toObjList() {
		return new Object[] {name,id,usage,fine};
	}
}
