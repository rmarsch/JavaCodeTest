package codetest.java.rich.marscher.file.input.parser;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.format.UnsupportedDataFileFormatException;
import codetest.java.rich.marscher.model.DataObject;
import codetest.java.rich.marscher.model.Person;

/**
 * FileParserBuilder abstracts building the appropriate concrete FileParser class
 * from the user. It should be configured to support all implemented data Object types
 * and DataFileFormats.
 *
 */
public class FileParserBuilder {
	/**
	 * Builder method to build FileParsers. Uses Class of the data Object
	 * and a DataFileFormat to resolve a FileParser implementation suitable
	 * for parsing.
	 * @param dataObjectClass Class of the data Objects to parse
	 * @param format DataFileFormat of the file
	 * @return FileParser instance to handle
	 * @throws UnsupportedDataFileFormatException when the dataObjectClass is not a supported Class type
	 * @throws UnsupportedFileDataTypeException when the DataFileFormat is not supported for the dataObjectClass type
	 */
	public static FileParser<?> buildFileParser(Class<? extends DataObject> dataObjectClass, DataFileFormat format) throws UnsupportedDataFileFormatException, UnsupportedFileDataTypeException{
		
		if(format == null){
			throw new UnsupportedDataFileFormatException();
		}
		
		if(Person.class.equals(dataObjectClass)){
			if(DataFileFormat.COMMA_DATA_FILE_FORMAT.equals(format)){
				return new PersonCommaFileParser(format);
			}else if(DataFileFormat.PIPE_DATA_FILE_FORMAT.equals(format)){
				return new PersonPipeFileParser(format);
			} else if(DataFileFormat.SPACE_DATA_FILE_FORMAT.equals(format)){
				return new PersonSpaceFileParser(format);
			} 
			
			throw new UnsupportedDataFileFormatException();
		}
		
		throw new UnsupportedFileDataTypeException();
	}
}
