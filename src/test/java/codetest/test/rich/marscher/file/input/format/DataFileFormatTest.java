package codetest.test.rich.marscher.file.input.format;

import static org.junit.Assert.*;

import org.junit.Test;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;

/**
 * Testing to assert that all supported DataFileFormats are detected properly
 */
public class DataFileFormatTest {

	String TEST_PIPE_FORMAT = "Word | Another | SomeText | Here's More | Why,Not!@#$%^&*()_+";
	String TEST_COMMA_FORMAT = "Word, Another, SomeText, Here's More, Why|Not!@#$%^&*()_+";
	String TEST_SPACE_FORMAT = "Word Another SomeText Here's,More Why|Not!@#$%^&*()_+";
	String TEST_ASTERISK_FORMAT = "Word*Another*SomeText*Here'sMore*WhyNot!@#$%^&*()_+";
	
	@Test
	public void test() throws UnsupportedDataFileFormatException {
		//Test that proper DataFileFormats are resolved from corresponding strings written in the format
		assertEquals(DataFileFormat.PIPE_DATA_FILE_FORMAT, DataFileFormat.resolveDataFileFormat(TEST_PIPE_FORMAT));
		assertEquals(DataFileFormat.COMMA_DATA_FILE_FORMAT, DataFileFormat.resolveDataFileFormat(TEST_COMMA_FORMAT));
		assertEquals(DataFileFormat.SPACE_DATA_FILE_FORMAT, DataFileFormat.resolveDataFileFormat(TEST_SPACE_FORMAT));
		
		//Test case of an unsupported DataFileFormat
		UnsupportedDataFileFormatException exception = null;
		
		try{
			DataFileFormat.resolveDataFileFormat(TEST_ASTERISK_FORMAT);
		}catch(UnsupportedDataFileFormatException e){
			exception = e;
		}
		
		assertNotNull(exception);
	}

}
