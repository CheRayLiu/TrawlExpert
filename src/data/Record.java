package data;

import java.io.Serializable;

import data.biotree.BioTree;

public class Record implements Comparable<Record>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3564218991166319058L;
	private final int eventId;
	private final String occurId;
	private final int taxonId;
	private final int individualCount;
	
	private final float latitude;
	private final float longitude;
	

	private final Date recDate;
	
	
//	public static void main(String[] args) {
//		///test main
//		Record record = new Record(1111,"11",11,111,(float) 94.56, 94,1998,1,9);
//		System.out.println(record.toString());
//	}

	/**
	 * Initialize Record abstract object
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
	 * @return The eventid of the record
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
	
	public String toString() {
		String format = "|%1$-45s|%2$-15s|%3$-15s|%4$-15s|%5$-15s|%6$-15s\n";
		String num = String.format(format, BioTree.getTaxonRecord(this.taxonId).getName(),this.individualCount,this.longitude, this.latitude, this.recDate.getYear(),this.recDate.getMonth(),this.recDate.getDay());
		for (int i = 0; i < 15*10 ;i++ ) {
			num += "_";
		}

		
		return num;
		}

	@Override
	public int compareTo(Record arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
