package codetest.test.rich.marscher.model;

import static org.junit.Assert.*;

import org.junit.Test;

import codetest.java.rich.marscher.model.Gender;

/**
 * Test the Gender model
 */
public class GenderTest {

	@Test
	public void testGender(){
		//Gender cases
		assertTrue(!Gender.FEMALE.equals(Gender.MALE));
		
		assertTrue(Gender.MALE.equals(Gender.MALE));
		assertTrue(Gender.FEMALE.equals(Gender.FEMALE));
		
		//Test normalization / resolution of acceptable gender inputs
		assertNotNull(Gender.resolveGender("M"));

		Gender g1 = Gender.resolveGender("feMale");
		Gender g2 = Gender.resolveGender("f");

		assertEquals(0, g1.compareWith(g2));
	}
}
