package codetest.test.rich.marscher.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import codetest.java.rich.marscher.model.Gender;
import codetest.java.rich.marscher.model.Person;

/**
 * Test the Person object
 */
public class PersonTest {

	Person p1, p2;

    @Before
	public void setUp() throws Exception {
		p1 = new Person();
		p2 = new Person();
	}

	@Test
	public void testModels(){
	    try { setUp(); } catch(Exception e){ } //Maven doesn't seem to recognized the @Before annotation

		//Test the equals implementation
		assertTrue(!p1.equals(null));
		assertTrue(!p1.equals("Different object type"));
		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());

		testDateOfBirth();
		testFavoriteColor();
		testFirstName();
		testGender();
		testLastName();
		testMiddleName();
	}

	private void testDateOfBirth(){
		//Date of birth cases
		Calendar cal = Calendar.getInstance();

		p1.setDateOfBirth(cal.getTime());

		assertTrue(!p1.equals(p2));

		cal.add(Calendar.DATE, 1);

		p2.setDateOfBirth(cal.getTime());

		assertTrue(!p1.equals(p2));

		p2.setDateOfBirth(p1.getDateOfBirth());

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());

		//Test the parse exception on the Person constructor
		ParseException e = null;

		try{
			new Person(null,null,null,null,null,"11-29-1986");
		}catch(ParseException pe){
			e = pe;
		}

		Assert.assertNotNull(e);

		e= null;

		try{
			new Person(null,null,null,null,null,"01/09/1986");
		}catch(ParseException pe){
			e = pe;
		}

		Assert.assertNull(e);
	}

	private void testFavoriteColor(){
		//Favorite color cases
		p1.setFavoriteColor("Red");

		assertTrue(!p1.equals(p2));

		p2.setFavoriteColor("red");

		assertTrue(!p1.equals(p2));

		p2.setFavoriteColor("Red");

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}

	private void testFirstName(){
		//First name cases
		p1.setFirstName("Richard");

		assertTrue(!p1.equals(p2));

		p2.setFirstName("Tyler");

		assertTrue(!p1.equals(p2));

		p2.setFirstName("Richard");

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}

	private void testGender(){
		//Gender cases
		p1.setGender(Gender.MALE);

		assertTrue(!p1.equals(p2));

		p2.setGender(Gender.FEMALE);

		assertTrue(!p1.equals(p2));

		//Test normalization / resolution of acceptable gender inputs
		p2.setGender(Gender.resolveGender("M"));

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());

		p1.setGender(Gender.resolveGender("feMale"));
		p2.setGender(Gender.resolveGender("f"));

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}

	private void testLastName(){
		//Last Name cases
		p1.setLastName("Marscher");

		assertTrue(!p1.equals(p2));

		p2.setLastName("Triggs");

		assertTrue(!p1.equals(p2));

		p2.setLastName("Marscher");

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}



	private void testMiddleName(){
		//Middle name cases
		p1.setMiddleName("R");

		assertTrue(!p1.equals(p2));

		p2.setMiddleName("T");

		assertTrue(!p1.equals(p2));

		p2.setMiddleName("R");

		assertTrue(p1.equals(p2));
		assertEquals(p1.hashCode(), p2.hashCode());
	}

}
