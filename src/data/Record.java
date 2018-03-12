package data;

public class Record implements Comparable<Record> {
	private final int eventId;
	private final String occurId;
	private final int taxonId;
	private final int individualCount;
	
	private final float latitude;
	private final float longitude;
	

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
	
	@Override
	public int compareTo(Record o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
