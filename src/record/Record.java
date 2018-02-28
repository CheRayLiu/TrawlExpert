package record;

public class Record {
	private final int eventId;
	private final String occurId;
	private final int taxonId;
	private final TaxonType taxon;
	private final int count;
	
	private final float latitude;
	private final float longitude;
	
	private final int year;
	private final int month;
	private final int day;
	

	/**
	 * Initialize Record abstract object
	 */
	public Record( int eventId, String occurId, int taxonId, TaxonType taxon, int count, float latitude, float longitude, String locality, int depth , int year, int month, int day, int hour, int minute ) {
		this.eventId = eventId;
		this.occurId = occurId;
		this.taxonId = taxonId;
		this.taxon = taxon;
		this.count = count;
		
		this.latitude = latitude;
		this.longitude = longitude;
	
		this.year = year;
		this.month = month;
		this.day = day;
		
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
	 * @return The occurence ID of the record
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
	 * Gets the taxon type.
	 * 
	 * @return The taxon type of the record
	 */
	public TaxonType getTaxonType() {
		return taxon;
	}
	/**
	 * Gets the individual count of the record
	 * 
	 * @return The individual count of the record
	 */
	public int getCount() {
		return count;
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

}
