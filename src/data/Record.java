package data;

import java.io.Serializable;

import data.biotree.BioTree;

/**
 * Represents a single record in the dataset.
 * @author TrawlStars, Inc.
 *
 */
public class Record implements Comparable<Record>, Serializable {
	/**
	 * Serializable version id of the current class.
	 */
	private static final long serialVersionUID = 3564218991166319058L;
	/**
	 * The event ID of the record.
	 */
	private final int eventId;
	/**
	 * The unique occurance id of the record.
	 */
	private final String occurId;
	/**
	 * The taxon id of the record.
	 */
	private final int taxonId;
	/**
	 * The number of individuals found in this record.
	 */
	private final int individualCount;
	/**
	 * The latitude of the record.
	 */
	private final float latitude;
	/**
	 * The longitude of the record.
	 */
	private final float longitude;
	/**
	 * The date the record was recorded.
	 */
	private final Date recDate;

	/**
	 * Initialize Record abstract data type
	 */
	public Record( int eventId, String occurId, int taxonId, int individualCount, float latitude, float longitude, int year, int month, int day) {
		this.eventId = eventId;
		this.occurId = occurId;
		this.taxonId = taxonId;
		this.individualCount = individualCount;
		
		this.latitude = latitude;
		this.longitude = longitude;
	
		
		this.recDate = new Date(year,month,day);
		
	}
	
	/**
	 * Gets the event ID
	 * 
	 * @return The event ID of the record
	 */
	public int getEventId() {
		return eventId;
	}
	/**
	 * Gets the occurrence ID
	 * 
	 * @return The occurrence ID of the record
	 */
	public String getOccurId() {
		return occurId;
	}
	/**
	 * Gets the taxon ID
	 * 
	 * @return The taxon ID of the record
	 */
	public int getTaxonId() {
		return taxonId;
	}
	/**
	 * Gets the individual count of the record
	 * 
	 * @return The individual count of the record
	 */
	public int getCount() {
		return individualCount;
	}
	/**
	 * Gets latitude of the record
	 * 
	 * @return The latitude of the record
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * Gets longitude of the record
	 * 
	 * @return The longitude of the record
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Gets date of the record
	 * 
	 * @return The date of the record
	 */
	public Date getDate() {
		return recDate;
	}
	
	/**
	 * Converts the record to its string representation for printing to the console.
	 * @return The string representation of the record in a table row format.
	 */
	public String toString() {
		String format = "|%1$-45s|%2$-15s|%3$-15s|%4$-15s|%5$-15s|%6$-15s\n";
		String num = String.format(format, BioTree.getTaxonRecord(this.taxonId).getName(),this.individualCount,this.longitude, this.latitude, this.recDate.getYear(),this.recDate.getMonth(),this.recDate.getDay());
		for (int i = 0; i < 15*10 ;i++ ) {
			num += "_";
		}

		
		return num;
		}

	/**
	 * Compare two records. At present, always returns 0. Should always be used with the GeneralCompare
	 * to select the proper comparison method.
	 */
	@Override
	public int compareTo(Record arg0) {
		return 0;
	}
	
	

}
