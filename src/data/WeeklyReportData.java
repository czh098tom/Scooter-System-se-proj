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
	 * Initialize the {@link WeeklyReportData} by name, ID of user, usage, fine and email.
	 * @param name : Name of user.
	 * @param id : ID of user.
	 * @param usage : Usage time (in minutes).
	 * @param fine : Fine count.
	 * @param email : Email address of the user.
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
	 * @return An object typed String with several lines of user information (String)
	 */
	public String toString() {
		return "User name:"+name+"\r\nUser ID: "
				+id+"\r\nTotal using time: "
				+usage+" minute(s)\r\nFine count last week: "+fine+"\r\n";
	}
	
	/**
	 * Generate an object list for JTable
	 * @return return an object list of {@link WeeklyReportData}
	 */
	public Object[] toObjList() {
		return new Object[] {name,id,usage,fine};
	}
}
