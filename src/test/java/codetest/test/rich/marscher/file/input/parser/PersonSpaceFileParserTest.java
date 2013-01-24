package codetest.test.rich.marscher.file.input.parser;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;
import codetest.java.rich.marscher.file.input.parser.FileParserBuilder;
import codetest.java.rich.marscher.file.input.parser.InvalidDataLineException;
import codetest.java.rich.marscher.file.input.parser.PersonSpaceFileParser;
import codetest.java.rich.marscher.file.input.parser.UnsupportedFileDataTypeException;
import codetest.java.rich.marscher.model.Person;

/**
 * Test to verify ability of PersonCommaFileParser to parse a Person object
 * from a space separated data string
 */
public class PersonSpaceFileParserTest {

	static final String TEST_SPACE_PERSON_LINE = "LastName FirstName MiddleInitial m  11-29-1986   Color";
	static final String NON_SPACE_PERSON_LINE = "LastName.FirstName.MiddleInitial.m.11-29-1986.Color";
	static final String SPACE_NON_PERSON_LINE = "Helmet Shoulderpads Elbowpads Gloves Cup Pants Kneepads Skates";
	static final String WRONG_TIMESTAMP_LINE = "LastName FirstName MiddleInitial m  11.29.1986 Color";

	@Test
	public void test() throws UnsupportedDataFileFormatException, UnsupportedFileDataTypeException, InvalidDataLineException {
		PersonSpaceFileParser personSpaceFileParser = (PersonSpaceFileParser) FileParserBuilder.buildFileParser(Person.class, DataFileFormat.SPACE_DATA_FILE_FORMAT);
		
		//Test strings, valid and non-valid
		Person person = personSpaceFileParser.readObject(TEST_SPACE_PERSON_LINE);

		assertNotNull(person);

		//Test case where the separator is not a space
		InvalidDataLineException exception = null;

		try{
			personSpaceFileParser.readObject(NON_SPACE_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where the separator is a space but the data is not a person
		exception = null;

		try{
			personSpaceFileParser.readObject(SPACE_NON_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where the timestamp is in the wrong format
		exception = null;

		try{
			personSpaceFileParser.readObject(WRONG_TIMESTAMP_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}
		
		assertNotNull(exception);
	}

}
