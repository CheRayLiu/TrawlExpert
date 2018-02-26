package record;

public class Record {
	private final int eventId;
	private final String occurId;
	private final int speciesId;
	private final int count;
	
	private final float latitude;
	private final float longitude;
	private final String locality;
	private final int depth;
	
	private final int year;
	private final int month;
	private final int day;
	private final int hour;
	private final int minute;
	

	/**
	 * Initialize Record abstract object
	 */
	public Record( int eventId, String occurId, int speciesId, int count, float latitude, float longitude, String locality, int depth , int year, int month, int day, int hour, int minute ) {
		this.eventId = eventId;
		this.occurId = occurId;
		this.speciesId = speciesId;
		this.count = count;
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.locality = locality;
		this.depth = depth;
		
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		
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
		return speciesId;
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
	 * Gets locality of the object
	 * 
	 * @return The locality of the object
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * Gets Depth of the object
	 * 
	 * @return The depth of the object
	 */
	public int getDepth() {
		return depth;
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


	/**
	 * Gets hour of the object
	 * 
	 * @return The hour of the object
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Gets minute of the object
	 * 
	 * @return The minute of the object
	 */
	public int getMinute() {
		return minute;
	}

}
