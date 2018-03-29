/**
 * 
 */
package sort;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author HaleyGlavina
 *
 */
public class MergeSortTest {

	private GeneralCompare<Integer> b1;
	private Integer[] test1 = {3, 4, 2, 1, 5, 7, 9, 10, 11};
	private Integer[] test2 = {5, 5, 23023948, -2, -10000};
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link sort.MergeSort#sort(java.lang.Comparable<T>[], int, int, sort.GeneralCompare)}.
	 */
	@Test
	public void testSort() {
		MergeSort.sort(test1, 0, test1.length - 1, b1);
		
		Integer[] expect = {1, 2, 3, 4, 5, 7, 9, 10, 11};
		for (int i = 0 ; i < expect.length ; i++) {
			if (expect[i] != test1[i])
				fail("MergeSort test1 fails.");
		}
	}
	
	@Test
	public void testBigValues() {
		MergeSort.sort(test2, 0, test2.length - 1, b1);
		
		Integer[] expect2 = {-10000, -2, 5, 5, 23023948};
		for (int i = 0 ; i < 5 ; i++) {
			if (!expect2[i].equals(test2[i]))
				fail("MergeSort test2 fails.");
		}
	}
}
