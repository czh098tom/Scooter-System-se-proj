package data;

public class WeeklyReportData {
	/**
	 * Store user ID and name in report.
	 */
	private final String name,id;
	/**
	 * Store user usage minutes in report.
	 */
	private final long usage;
	/**
	 * Store user fine count in report.
	 */
	private final int fine;
	
	/**
	 * Initialize report data by user ID, name, usage minutes and fine count.
	 * @param name : User ID in report.
	 * @param id : User name in report.
	 * @param usage : User usage in report in minutes.
	 * @param fine : User fine in report in count.
	 */
	public WeeklyReportData(String name,String id,long usage,int fine) {
		this.name=name;
		this.id=id;
		this.usage=usage;
		this.fine=fine;
	}
	
	public String toString() {
		return "";
	}
	
	public Object[] toObjList() {
		return new Object[] {name,id,usage,fine};
	}
}
