package codetest.test.rich.marscher.file.input.parser;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;
import codetest.java.rich.marscher.file.input.parser.FileParserBuilder;
import codetest.java.rich.marscher.file.input.parser.InvalidDataLineException;
import codetest.java.rich.marscher.file.input.parser.PersonCommaFileParser;
import codetest.java.rich.marscher.file.input.parser.UnsupportedFileDataTypeException;
import codetest.java.rich.marscher.model.Person;

/**
 * Test to verify ability of PersonCommaFileParser to parse a Person object
 * from a Comma separated data string
 */
public class PersonCommaFileParserTest {

	//Test strings, valid and non-valid
	static final String TEST_COMMA_PERSON_LINE = "Last, First , male,Color, 11/29/1986";
	static final String NON_COMMA_PERSON_LINE = "Last|First|male|Color|11/29/1986";
	static final String COMMA_NON_PERSON_LINE = "Helmet, Shoulder Pads, Elbow Pads, Gloves, Cup, Pants, Kneepads, Skates";
	static final String WRONG_TIMESTAMP_LINE = "Last, First, male, Color, 1986|11|29";

	@Test
	public void test() throws UnsupportedDataFileFormatException, UnsupportedFileDataTypeException, InvalidDataLineException {
		PersonCommaFileParser personCommaFileParser = (PersonCommaFileParser) FileParserBuilder.buildFileParser(Person.class, DataFileFormat.COMMA_DATA_FILE_FORMAT);

		//Test valid case
		Person person = personCommaFileParser.readObject(TEST_COMMA_PERSON_LINE);

		assertNotNull(person);

		//Test case where the separator is not a comma
		InvalidDataLineException exception = null;

		try{
			personCommaFileParser.readObject(NON_COMMA_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where the separator is a comma but the data is not setup for a person object
		exception = null;

		try{
			personCommaFileParser.readObject(COMMA_NON_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where the timestamp is in the wrong format
		exception = null;

		try{
			personCommaFileParser.readObject(WRONG_TIMESTAMP_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}
		
		assertNotNull(exception);
	}

}
