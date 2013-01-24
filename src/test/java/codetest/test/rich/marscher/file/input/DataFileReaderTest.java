package codetest.test.rich.marscher.file.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import codetest.java.rich.marscher.CodeTest;
import codetest.java.rich.marscher.file.input.DataFileReader;
import codetest.java.rich.marscher.model.Person;
import codetest.test.rich.marscher.file.AbstractFileTest;

/**
 * Test DataFileReader. Runs as a bit of an integration test as there is no mocking setup.
 * Verifies the encapsulation of DataFileReader returns expected results regardless of
 * its implementation.
 */
public class DataFileReaderTest extends AbstractFileTest {

	@Test
	public void testInputFiles(){
	    try { setUp(); } catch(Exception e){ } //Maven doesn't seem to recognized the @Before annotation
	
	    DataFileReader<Person> dataFileReader = new DataFileReader<Person>();
	    
		//Test cases where PersonFileParser cannot open or interpret file
		assertNull(dataFileReader.getFileData(Person.class, null));
		assertNull(dataFileReader.getFileData(Person.class, INVALID_INPUT_FILE));

		testCommaFileParsing(dataFileReader);
		testPipeFileParsing(dataFileReader);
		testSpaceFileParsing(dataFileReader);
	}

	private void testCommaFileParsing(DataFileReader<Person> dataFileReader){
		//Test comma file parsing
		Collection<Person> parsedCommaPeople = dataFileReader.getFileData(Person.class, CodeTest.COMMA_FILE_PATH);
        
		assertNotNull(parsedCommaPeople);
		assertEquals(commaPeople.size(),parsedCommaPeople.size());
		assertTrue(parsedCommaPeople.containsAll(commaPeople));
	}

	private void testPipeFileParsing(DataFileReader<Person> dataFileReader){
		//Test pipe file parsing
		Collection<Person> parsedPipePeople = dataFileReader.getFileData(Person.class, CodeTest.PIPE_FILE_PATH);

		assertNotNull(parsedPipePeople);
		assertEquals(pipePeople.size(),parsedPipePeople.size());
		assertTrue(parsedPipePeople.containsAll(pipePeople));
	}

	private void testSpaceFileParsing(DataFileReader<Person> dataFileReader){
		//Test pipe file parsing
		Collection<Person> parsedSpacePeople = dataFileReader.getFileData(Person.class, CodeTest.SPACE_FILE_PATH);

		assertNotNull(parsedSpacePeople);
		assertEquals(spacePeople.size(),parsedSpacePeople.size());
		assertTrue(parsedSpacePeople.containsAll(spacePeople));
	}

}
