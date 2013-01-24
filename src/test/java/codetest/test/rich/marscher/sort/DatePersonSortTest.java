package codetest.test.rich.marscher.sort;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.sort.DataObjectSort;
import codetest.java.rich.marscher.sort.DatePersonSort;

/**
 * Test the DatePersonSort class to verify it sorts correctly
 */
public class DatePersonSortTest {

	Person p1, p2;

    @Before
	public void setUp() throws Exception {		
		p1 = new Person();
		p2 = new Person();
	}

	@Test
	public void testDateSort(){
	    try { setUp(); } catch(Exception e){ } //Maven doesn't seem to recognized the @Before annotation
	
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.HOUR, -2);
		Date before = cal.getTime();
		cal.add(Calendar.HOUR, 5);
		Date after = cal.getTime();

		DatePersonSort ds = new DatePersonSort();

		//Case 1: Dates are null (arbitrarily put first argument ahead in sort)
		assertEquals(DataObjectSort.BEFORE, ds.compare(p1, p2));

		p1.setDateOfBirth(now);

		//Case 2: First date before a null date
		assertEquals(DataObjectSort.BEFORE, ds.compare(p1, p2));

		p2.setDateOfBirth(now);
		p1.setLastName("Bonk");
		p2.setLastName("Bouillon");

		//Case 3: Dates equal (last name determines lexicographically)
		assertEquals(DataObjectSort.BEFORE, ds.compare(p1, p2));
		p1.setLastName("Kournikova");
		assertEquals(DataObjectSort.AFTER, ds.compare(p1,p2));

		p1.setLastName(null);
		p2.setLastName(null);

		p1.setDateOfBirth(before);

		//Case 4: First date before
		assertEquals(DataObjectSort.BEFORE, ds.compare(p1, p2));

		p1.setDateOfBirth(after);

		//Case 5: First date after
		assertEquals(DataObjectSort.AFTER, ds.compare(p1, p2));
	}

}
