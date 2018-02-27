package record;

public class Record {
	private final int eventId;
	private final String occurId;
	private final int taxonId;
	private final int count;
	
	private final float latitude;
	private final float longitude;
	
	private final int year;
	private final int month;
	private final int day;
	

	/**
	 * Initialize Record abstract object
	 */
	public Record( int eventId, String occurId, int taxonId, int count, float latitude, float longitude, String locality, int depth , int year, int month, int day, int hour, int minute ) {
		this.eventId = eventId;
		this.occurId = occurId;
		this.taxonId = taxonId;
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
	 * @return The eventid of the object
	 */
	public int getEventId() {
		return eventId;
	}
	/**
	 * Gets the occurrence ID
	 * 
	 * @return The occurence ID of the object
	 */
	public String getOccurId() {
		return occurId;
	}
	/**
	 * Gets the species ID
	 * 
	 * @return The species ID of the object
	 */
	public int getSpeciesId() {
		return taxonId;
	}
	/**
	 * Gets the count of the object
	 * 
	 * @return The count of the object
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Gets latitude of the object
	 * 
	 * @return The latitude of the object
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * Gets longitude of the object
	 * 
	 * @return The longitude of the object
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Gets year of the object
	 * 
	 * @return The year of the object
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets month of the object
	 * 
	 * @return The month of the object
	 */
	public int getMonth() {
		return month;
	}


	/**
	 * Gets day of the object
	 * 
	 * @return The day of the object
	 */
	public int getDay() {
		return day;
	}

}
