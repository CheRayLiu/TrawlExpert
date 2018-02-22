package biotree;

public class Species {
	private final String species;
	private final String bClass;
	private final String order;
	private final String family;
	private final String genus;
	
	public Species(String species, String bClass, String order, String family, String genus) {
		this.species = species;
		this.bClass  = bClass;
		this.order   = order;
		this.family  = family;
		this.genus   = genus;
	}
	
	public String getSpecies() {
		return this.species;
	}
	
	public String getBClass() {
		return this.bClass;
	}
	
	public String getOrder() {
		return this.order;
	}
	
	public String getFamily() {
		return this.family;
	}
	
	public String getGenus() {
		return this.genus;
	}
}
