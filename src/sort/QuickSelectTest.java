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
public class QuickSelectTest {

	private GeneralCompare<Integer> b1;
	private Integer[] test1 = {4, 6, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	private Integer[] test2 = {10};
	private Integer[] test3 = {-200, -1000, 2048, 1996, 0};
	
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
	 * Test method for {@link sort.QuickSelect#median(java.lang.Comparable<T>[], search.GeneralCompare)}.
	 * Ensure each median matches what is expected
	 */
	@Test
	public void testMedian() {
		QuickSelect.median(test1, b1);
		assert(test1[test1.length/2] == 2);
		
		QuickSelect.median(test2, b1);
		assert(test2[test2.length/2] == 10);
		
		QuickSelect.median(test3, b1);
		assert(test3[test3.length/2] == 0);
	}

	/**
	 * Test method for {@link sort.QuickSelect#partialSort(java.lang.Comparable<T>[], int, search.GeneralCompare)}.
	 * Ensure the kth largest element matches what is expected
	 */
	@Test
	public void testPartialSort() {		
		QuickSelect.partialSort((Comparable<Integer>[]) test1, 5, b1);
		assert(test1[test1.length - 5] == 2);
		
		QuickSelect.partialSort((Comparable<Integer>[]) test2, 1, b1);
		assert(test2[test2.length - 1] == 10);
		
		QuickSelect.partialSort((Comparable<Integer>[]) test3, 4, b1);
		assert(test3[test3.length - 4] == -200);
	}
}
