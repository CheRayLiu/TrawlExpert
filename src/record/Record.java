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
	
	
	public int getEventId() {
		return eventId;
	}

	public String getOccurId() {
		return occurId;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public int getCount() {
		return count;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}


	public String getLocality() {
		return locality;
	}


	public int getDepth() {
		return depth;
	}


	public int getYear() {
		return year;
	}


	public int getMonth() {
		return month;
	}


	public int getDay() {
		return day;
	}



	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}

}
