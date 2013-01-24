package codetest.test.rich.marscher.file.input.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;
import codetest.java.rich.marscher.file.input.parser.FileParser;
import codetest.java.rich.marscher.file.input.parser.FileParserBuilder;
import codetest.java.rich.marscher.file.input.parser.PersonCommaFileParser;
import codetest.java.rich.marscher.file.input.parser.PersonPipeFileParser;
import codetest.java.rich.marscher.file.input.parser.PersonSpaceFileParser;
import codetest.java.rich.marscher.file.input.parser.UnsupportedFileDataTypeException;
import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.model.DataObject;

/**
 * Test the FileParserBuilder to verify all supported FileParser built
 */
public class FileParserBuilderTest {

	@Test
	public void test() throws UnsupportedDataFileFormatException, UnsupportedFileDataTypeException {
		
		FileParser<?> fileParser = null;

		//Verify PersonCommaFileParser supported
		fileParser = FileParserBuilder.buildFileParser(Person.class, DataFileFormat.COMMA_DATA_FILE_FORMAT);

		assertTrue(fileParser instanceof PersonCommaFileParser);

		//Verify PersonPipeFileParser supported
		fileParser = FileParserBuilder.buildFileParser(Person.class, DataFileFormat.PIPE_DATA_FILE_FORMAT);

		assertTrue(fileParser instanceof PersonPipeFileParser);

		//Verify PersonSpaceFileParser supported
		fileParser = FileParserBuilder.buildFileParser(Person.class, DataFileFormat.SPACE_DATA_FILE_FORMAT);

		assertTrue(fileParser instanceof PersonSpaceFileParser);

		//Verify exception for missing DataFileFormat
		UnsupportedDataFileFormatException exception = null;

		try{
			fileParser = FileParserBuilder.buildFileParser(Person.class, null);
		}catch(UnsupportedDataFileFormatException e){
			exception = e;
		}

		assertNotNull(exception);

		//Verify exception for unsupported DataObject type
		exception = null;

		UnsupportedFileDataTypeException fileTypeException = null;
		
		try{
			fileParser = FileParserBuilder.buildFileParser(UnsupportedDataObject.class, DataFileFormat.COMMA_DATA_FILE_FORMAT);

		}catch(UnsupportedFileDataTypeException e){
			fileTypeException = e;
		}

		assertNotNull(fileTypeException);
	}

	//Private class to create an Unsupported implementation of the DataObject interface
	private class UnsupportedDataObject implements DataObject {

		@Override
		public String toDataString() {
			return "";
		}
		
	}
}
