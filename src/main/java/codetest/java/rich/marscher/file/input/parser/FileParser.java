package codetest.java.rich.marscher.file.input.parser;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.model.DataObject;

/**
 * Abstract class representing a FileParser
 * a FileParser is expected to use a DataFileFormat
 * to read objects of type T out of lines of data 
 *
 * @param <T> Type of object to read
 */
public abstract class FileParser<T extends DataObject> {

	private DataFileFormat format = null;

	FileParser(DataFileFormat format){
	    this.format = format;
	}
	
	/**
	 * Returns the DataFileFormat of this FileParser
	 * @return DataFileFormat object
	 */
	public DataFileFormat getFormat(){
		return format;
	}
	
	/**
	* Abstract method that uses the DataFileFormat and the T type to parse an Object T from a given line from a file conforming to the DataFileFormat.
	* @line - A line from a file conforming to this object's DataFileFormat
	* @return - A object of type T parsed from the line, or null if it could not be parsed
	*/
	public abstract T readObject(String line) throws InvalidDataLineException;

	
}
