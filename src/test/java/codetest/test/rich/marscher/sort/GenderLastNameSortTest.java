package codetest.test.rich.marscher.sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import codetest.java.rich.marscher.model.Gender;
import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.sort.DataObjectSort;
import codetest.java.rich.marscher.sort.GenderLastNamePersonSort;

/**
 * Test the GenderLastNameSortTest class to verify it sorts correctly
 */
public class GenderLastNameSortTest {

	Person p1, p2;

	@Before
	public void setUp() throws Exception {		
		p1 = new Person();
		p2 = new Person();
	}

	//Implicitly tests the LastNameSort as well
	@Test
	public void testGenderLastNameSort(){

		GenderLastNamePersonSort glns = new GenderLastNamePersonSort();

		//Case 1: null genders (1st argument before)
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p1.setGender(Gender.MALE);

		//Case 2: Male vs. null is before
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p1.setGender(Gender.FEMALE);

		//Case 3: Female vs. null is before
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p2.setGender(Gender.FEMALE);

		//Case 4: Female vs. Female (1st argument before)
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p2.setGender(Gender.MALE);

		//Case 5: Female vs. Male
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p1.setGender(null);

		//Case 6: null vs Male (null after Male)
		assertEquals(DataObjectSort.AFTER, glns.compare(p1, p2));

		p2.setGender(Gender.FEMALE);

		//Case 7: null vs. Femail (null after Female)
		assertEquals(DataObjectSort.AFTER, glns.compare(p1, p2));

		//Last Name cases

		p1.setGender(Gender.FEMALE);
		p1.setLastName("Hingis");

		//Case 8: Female-Last name vs. Female-Null
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p2.setLastName("Kelly");

		//Case 9: Matching gender, last name before
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p1.setLastName(null);

		//Case 10: Matching gender, null last name after
		assertEquals(DataObjectSort.AFTER, glns.compare(p1, p2));

		p1.setLastName("Kelly");

		//Case 11: Matching gender and last name (1st argument before)
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));

		p1.setLastName("Kournikova");

		//Case 12: Matching gender, last name after
		assertEquals(DataObjectSort.AFTER, glns.compare(p1, p2));

		p2.setLastName("Bishop");
		p2.setGender(Gender.MALE);

		//Case 13: Female with higher last name is before male with lower last name
		assertEquals(DataObjectSort.BEFORE, glns.compare(p1, p2));
	}

}
