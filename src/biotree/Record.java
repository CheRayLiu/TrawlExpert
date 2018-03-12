package biotree;

public class Record implements Comparable<Record> {
	private final int eventId;
	private final String occurId;
	private final int taxonId;
	private final int individualCount;
	
	private final float latitude;
	private final float longitude;
	
	private final int year;
	private final int month;
	private final int day;
	private final Date recDate;
	

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
	
		this.year = year;
		this.month = month;
		this.day = day;
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
	 * Gets year of the record
	 * 
	 * @return The year of the record
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets month of the record
	 * 
	 * @return The month of the record
	 */
	public int getMonth() {
		return month;
	}


	/**
	 * Gets day of the record
	 * 
	 * @return The day of the record
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets date of the record
	 * 
	 * @return The date of the record
	 */
	public Date getDate() {
		return recDate;
	}
	
	@Override
	public int compareTo(Record o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
