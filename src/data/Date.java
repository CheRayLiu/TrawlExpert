package data;

import java.io.Serializable;
	/**
	 * Date ADT for Records
	 */
public class Date implements Comparable<Date>, Serializable {
	
	/**
	 * Version id for serilization.
	 */
	private static final long serialVersionUID = -5256185778691324647L;
	/**
	 * The year of the date
	 */
	private final int year;
	/**
	 * The month of the date
	 */
	private final int month;
	/**
	 * The day of the date
	 */
	private final int day;
	
	/**
	 * Initializes the object
	 */
	public Date (int year, int month, int day) {
		this.year= year;
		this.month= month;
		this.day = day;
		
	}
	
	/**
	 * Gets the year
	 * 
	 * @return The year of the date
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * Gets the month
	 * 
	 * @return The month of the date
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Gets the day
	 * 
	 * @return The day of the date
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Gets the day
	 * @param other -date object that it's comparing to
	 * @return comparedToValue -1 if greater, -1 if smaller and 0 if equal
	 */
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
