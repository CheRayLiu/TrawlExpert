package data;

public class Date implements Comparable<Date> {
	private final int year;
	private final int month;
	private final int day;
	
	public Date (int year, int month, int day) {
		this.year= year;
		this.month= month;
		this.day = day;
		
	}
	
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}


	public int compareTo(Date other) {
		
		if (this.year > other.getYear())  return 1;
		else if (this.year < other.getYear()) return -1;
		else {
			if (this.month > other.getMonth()) return 1;
			else if (this.month < other.getMonth()) return -1;
			else {
				if (this.day > other.getDay()) return 1;
				else if (this.day < other.getDay()) return -1;
				else return 0;
			}
		}
	}
	
	
	
}
