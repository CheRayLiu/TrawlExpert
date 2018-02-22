package biotree;

public class BioTree {
	private static Species[] species;
	private static int n;
	
	/**
	 * Initialize species abstract object
	 */
	public static void init() {
		species = new Species[500];
		n = 0;
	}

	/**
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to read from
	 */
	public static void init(String fn) {

	}

	/**
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to write to
	 */
	public static void write(String fn) {

	}

	/**
	 * Add a new species to the module
	 * 
	 * @param s
	 *            New Species to add.
	 * @return speciesid of new species entry
	 */
	public static int addSpecies(Species s) {
		species[n++] = s;
		return n;
	}

	/**
	 * Update an existing species object.
	 * 
	 * @param i
	 *            The index of the species to update.
	 * @param s
	 *            The new Species object to overwrite the old one with.
	 */
	public static void setSpecies(int i, Species s) {
		species[i] = s;
	}

	/**
	 * Get the species at a given index (speciesid).
	 * 
	 * @param i
	 *            The speciesid (index) of the species.
	 * @return The Species object.
	 */
	public static Species getSpecies(int i) {
		return species[i];
	}

	/**
	 * Search for a species by name.
	 * 
	 * @param s
	 *            The name of the species.
	 * @return The speciesid of the species or -1 if it is not found.
	 */
	public static int findSpecies(String s) {
		for (int i = 0; i < n; i++)
			if (species[i].getSpecies() == s)
				return i;
		return -1;
	}

	/**
	 * TODO: Implement
	 * 
	 * @param s
	 *            The name of the genus.
	 * @return Array of speciesid belonging to the genus.
	 */
	public static int[] findGenus(String s) {
		int[] dummy = { 1, 2, 3 };
		return dummy;
	}

	/**
	 * TODO: Implement
	 * 
	 * @param s
	 *            The name of the family.
	 * @return Array of speciesid belonging to the family.
	 */
	public static int[] findFamily(String s) {
		int[] dummy = { 1, 2, 3 };
		return dummy;
	}

	/**
	 * TODO: Implement
	 * 
	 * @param s
	 *            The name of the order.
	 * @return Array of speciesid belonging to the order.
	 */
	public static int[] findOrder(String s) {
		int[] dummy = { 1, 2, 3 };
		return dummy;
	}

}
