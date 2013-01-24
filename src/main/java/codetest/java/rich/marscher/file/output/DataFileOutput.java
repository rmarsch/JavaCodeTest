package codetest.java.rich.marscher.file.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.model.DataObject;
import codetest.java.rich.marscher.model.Person;

/**
 * Implementation of DataOutput that writes
 * output to a configured File
 *
 */
public class DataFileOutput implements DataOutput {

	private File outputFile = null;
	private FileWriter fileOut = null;

	/**
	 * Creates a DataFileOutput object and opens a FileWriter stream. Must use the close() method at least before the object is garbage collected.
	 * @param filepath path to the output file to use. Cannot create new folders in filepath.
	 * @throws IOException throws IOException if a FileWriter stream cannot be opened for the filePath
	 */
	public DataFileOutput(String filepath) throws IOException {
		outputFile = new File(filepath);
		fileOut = new FileWriter(outputFile);
	}

	/**
	 * Outputs a preface string and an Iteratable set of DataObject objects to the
	 * file opened by this DataFileOutput.
	 */
	public void printDataObjects(String preface, Iterator<? extends DataObject> dataObjects){
		try{
			fileOut.write(preface);
			fileOut.write(Util.OS_FILE_NEW_LINE);

			if(dataObjects != null){
				while(dataObjects.hasNext()){
					fileOut.write(dataObjects.next().toString());
					fileOut.write(Util.OS_FILE_NEW_LINE);
				}
				fileOut.write(Util.OS_FILE_NEW_LINE);
			}
		}catch(Exception e){
			Util.handleException(e, "Exception printing output");
		}	
	}

	/**
	 * Closes the FileWriter stream.
	 */
	public void close(){
		try{
			if(fileOut != null){
				fileOut.close();
			}
		}catch (Exception e){
			Util.handleException(e,"Exception closing file writer");
		}  finally {
			if(fileOut != null){
				try{
					fileOut.close();
				}catch(IOException e){
					Util.handleException(e, "Could not close output file properly!");
				}
			}
		}
	}
}
