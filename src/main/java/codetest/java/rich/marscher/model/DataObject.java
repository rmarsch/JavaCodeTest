package codetest.java.rich.marscher.model;

/**
 * Interface for DataObjects to read/write
 *
 */
public interface DataObject {
	
	/**
	 * Abstract method analogue to toString that is built
	 * to write the data in a tokenized format as a data
	 * string in one line.
	 * @return
	 */
	public String toDataString();
}
