package codetest.java.rich.marscher.file.output;

import java.util.Iterator;

import codetest.java.rich.marscher.model.DataObject;

/**
 * Interface for outputting a DataObject to an output stream 
 *
 */
public interface DataOutput {

	public void printDataObjects(String string, Iterator<? extends DataObject> dataObjects);
}
