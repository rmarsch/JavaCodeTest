package codetest.java.rich.marscher.file.output;

import java.util.Iterator;

import codetest.java.rich.marscher.model.DataObject;

/**
 * Implementation of DataOutput that writes
 * output to the System.out stream
 *
 */
public class DataSystemOutput implements DataOutput {

	/**
	 * Prints a preface and Iterable set of DataObject objects to the Standard Output stream
	 */
	public void printDataObjects(String preface, Iterator<? extends DataObject> dataObjects){

		System.out.println(preface);

		if(dataObjects != null){
			while(dataObjects.hasNext()){
				System.out.println(dataObjects.next().toDataString());
			}
			System.out.println("\n");
		}

	}

}
