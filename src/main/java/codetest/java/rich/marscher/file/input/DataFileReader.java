package codetest.java.rich.marscher.file.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.file.input.parser.FileParser;
import codetest.java.rich.marscher.file.input.parser.FileParserBuilder;
import codetest.java.rich.marscher.model.DataObject;

/**
 * DataFileReader written to pull a collection of objects of type T from a file
 * Depends on suitable implementations of parsers and supported data formats
 * @see FileParser
 * @see DataFileFormat
 * 
 * @param <T> Type of Data to read from file. 
 */
public class DataFileReader<T extends DataObject> {


	/**
	 * Takes a file path and attempts to parse T objects from the file where the objects are each on a new line and 
	 * their values are token separated.
	 * @param filePath filepath of a valid comma, pipe, or space separated file
	 * @return Collection of T objects parsed out of the input file. Null if an error opening or determining format of the file occurs
	 * or if the object of type T is not supported or the token-separator is not supported.
	 */
	public Collection<T> getFileData(Class<T> dataClass, String filePath){
	
		if(filePath == null){
			System.out.println("Error: null filename passed in.");
			return null;
		}		
	
		File f = openFile(filePath);

		if(f == null || !f.exists()){
			System.out.println("Error: File could not be opened");
			return null;
		}


		//Resolve input delimiters
		BufferedReader in = null;
		try{
			in = new BufferedReader(new java.io.FileReader(f));
			
			String firstLine = in.readLine();

			DataFileFormat dataFileFormat = DataFileFormat.resolveDataFileFormat(firstLine);

            @SuppressWarnings("unchecked")
			FileParser<T> fileParser = (FileParser<T>) FileParserBuilder.buildFileParser(dataClass, dataFileFormat);
			
			//If a parser was matched to the input file, parse T objects
			return fileParser != null ? retrieveCollection(fileParser, firstLine, in) : null;

		}catch(Exception e){
			Util.handleException(e, "Exception occurred initializing or retrieving data from the file");
		}finally{
			if(in != null)
				closeReader(in);
		}

		return null;
	}
	
	/**
	* Uses the file parser to retrieve a collection of all the objects of type T in the file
	* @parser - A FileParser pre-configured to parse the DataFileFormat of the file and objects of type T
	* @line - The first line of the file (typically read already to detect DataFileFormat) or null or an empty string if no lines have been read
	* @in - A open BufferedReader linked to the file to be processed
	* @return - A collection of the objects of type T parsed from the file
	*/
	private Collection<T> retrieveCollection(FileParser<T> parser, String line, BufferedReader in) throws Exception {
	    Collection<T> collection = new ArrayList<T>();
	    
	    do {
	        if(line != null && !"".equals(line.trim())){
	            T object = (T) parser.readObject(line);
	            collection.add(object);
	        }
	    } while ((line = in.readLine()) != null);
	    
	    return collection;
	}

	/**
	 * Instantiates a file object from a file path
	 */
	private File openFile(String filePath){
		File f = null;

		try{
			f = new File(filePath);
		}catch(RuntimeException e){
			Util.handleException(e, "Exception occurred opening file");
		}

		return f;
	}
	
	/**
	 * Closes a Reader object
	 */
	public void closeReader(Reader s){
		if(s != null){
			try{
				s.close();
			}catch(IOException e){
				Util.handleException(e, "Critically failed closing input stream");
			}
		}
	}
}
