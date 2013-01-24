package codetest.test.rich.marscher.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.sort.DataObjectSort;
import codetest.java.rich.marscher.sort.LastNamePersonSort;

/**
 * Test the LastNameSortTest class to verify it sorts correctly
 */
public class LastNameSortTest {

	Person p1, p2;

	@Before
	public void setUp() throws Exception {		
		p1 = new Person();
		p2 = new Person();
	}

	@Test
	public void testLastNameSort(){

		LastNamePersonSort lns = new LastNamePersonSort();

		//Last Name cases

		p1.setLastName("Hingis");

		//Case 1: Last name vs. Null
		assertEquals(DataObjectSort.BEFORE, lns.compare(p1, p2));

		p2.setLastName("Kelly");

		//Case 2: Last name before
		assertEquals(DataObjectSort.BEFORE, lns.compare(p1, p2));

		p1.setLastName(null);

		//Case 3: Null last name after
		assertEquals(DataObjectSort.AFTER, lns.compare(p1, p2));

		p1.setLastName("Kelly");

		//Case 4: Matching last name (1st argument before)
		assertEquals(DataObjectSort.BEFORE, lns.compare(p1, p2));

		p1.setLastName("Kournikova");

		//Case 5: Last name after
		assertEquals(DataObjectSort.AFTER, lns.compare(p1, p2));
	}

}
