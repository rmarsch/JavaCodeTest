package codetest.test.rich.marscher.file.input.parser;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;
import codetest.java.rich.marscher.file.input.parser.FileParserBuilder;
import codetest.java.rich.marscher.file.input.parser.InvalidDataLineException;
import codetest.java.rich.marscher.file.input.parser.PersonPipeFileParser;
import codetest.java.rich.marscher.file.input.parser.UnsupportedFileDataTypeException;
import codetest.java.rich.marscher.model.Person;

/**
 * Test to verify ability of PersonCommaFileParser to parse a Person object
 * from a pipe separated data string
 */
public class PersonPipeFileParserTest {
	//Test strings, valid and non-valid
	static final String TEST_PIPE_PERSON_LINE = "Last | First |Middle | m| Color|11-29-1986";
	static final String NON_PIPE_PERSON_LINE = "Last,First,Middle,m,Color,11-29-1986";
	static final String PIPE_NON_PERSON_LINE = "Helmet | Shoulder Pads | Elbow Pads | Gloves | Cup | Pants | Kneepads | Skates";
	static final String WRONG_TIMESTAMP_LINE = "Last | First | Middle | m | Color | 11.29.1986";

	@Test
	public void test() throws UnsupportedDataFileFormatException, UnsupportedFileDataTypeException, InvalidDataLineException {
		PersonPipeFileParser personPipeFileParser = (PersonPipeFileParser) FileParserBuilder.buildFileParser(Person.class, DataFileFormat.PIPE_DATA_FILE_FORMAT);

		//Test valid case
		Person person = personPipeFileParser.readObject(TEST_PIPE_PERSON_LINE);

		assertNotNull(person);

		//Test case where separator is not a pipe
		InvalidDataLineException exception = null;

		try{
			personPipeFileParser.readObject(NON_PIPE_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where separator is a pipe but the data is not a person
		exception = null;

		try{
			personPipeFileParser.readObject(PIPE_NON_PERSON_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}

		assertNotNull(exception);
		
		//Test case where the timestamp is in the wrong format
		exception = null;

		try{
			personPipeFileParser.readObject(WRONG_TIMESTAMP_LINE);
		}catch(InvalidDataLineException e){
			exception = e;
		}
		
		assertNotNull(exception);
	}

}
