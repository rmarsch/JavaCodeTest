package codetest.test.rich.marscher.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import codetest.java.rich.marscher.CodeTest;
import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.model.Person;

/**
 * Boiler plate values for test classes to extend to access.
 *
 */
public class AbstractFileTest {
	
	//Test paths for valid and invalid files
	public static final String INVALID_INPUT_FILE = "inputFiles"+ Util.OS_FILE_DELIM + "noseparator.txt";
	public static final String INVALID_OUTPUT_FILE = "notarealfolder" + Util.OS_FILE_DELIM + "fakefile.txt";
	public static final String EXPECTED_OUTPUT_FILE_PATH = "outputFiles" + Util.OS_FILE_DELIM + "model_output.txt";

	public static Collection<Person> commaPeople = null;
	public static Collection<Person> pipePeople = null;
	public static Collection<Person> spacePeople = null;

	public static File outputFile = null, expectedOutputFile = null;

	public static Person Abercrombie, Bishop, Kelly, Smith, Bonk, Bouillon, Kournikova, Hingis, Seles;	
	
	@Before
	public void setUp() throws Exception {
		//Setup the expected Collections
		commaPeople = new ArrayList<Person>();
		Abercrombie = new Person("Abercrombie","Neil",null,"Male","Tan","2/13/1943");
		Bishop = new Person("Bishop","Timothy",null,"Male","Yellow","4/23/1967");
		Kelly = new Person("Kelly","Sue",null,"Female","Pink","7/12/1959");
		commaPeople.add(Abercrombie);
		commaPeople.add(Bishop);
		commaPeople.add(Kelly);

		pipePeople = new ArrayList<Person>();
		Smith = new Person("Smith","Steve","D","M","Red","3/3/1985");
		Bonk = new Person("Bonk","Radek","S","M","Green","6/3/1975");
		Bouillon = new Person("Bouillon","Francis","G","M","Blue","6/3/1975");
		pipePeople.add(Smith);
		pipePeople.add(Bonk);
		pipePeople.add(Bouillon);

		spacePeople = new ArrayList<Person>();
		Kournikova = new Person("Kournikova","Anna","F","F","Red","6/3/1975");
		Hingis = new Person("Hingis","Martina","M","F","Green","4/2/1979");
		Seles = new Person("Seles","Monica","H","F","Black","12/2/1973");
		spacePeople.add(Kournikova);
		spacePeople.add(Hingis);
		spacePeople.add(Seles);

		//Setup the Files for output
		outputFile = new File(CodeTest.OUTPUT_FILE_PATH);
		expectedOutputFile = new File(EXPECTED_OUTPUT_FILE_PATH);
	}
	
	@Test
	public void testToSatisfyJUnit(){
		assertTrue(true);
	}
}
