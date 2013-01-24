package codetest.java.rich.marscher;

import java.util.Collection;
import java.util.TreeSet;

import codetest.java.rich.marscher.file.input.DataFileReader;
import codetest.java.rich.marscher.file.output.DataFileOutput;
import codetest.java.rich.marscher.file.output.DataOutput;
import codetest.java.rich.marscher.file.output.DataSystemOutput;
import codetest.java.rich.marscher.model.DataObject;
import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.sort.DatePersonSort;
import codetest.java.rich.marscher.sort.GenderLastNamePersonSort;
import codetest.java.rich.marscher.sort.LastNamePersonSort;


public class CodeTest {

	//Filepath for output
	public static final String OUTPUT_FILE_PATH = "outputFiles"+ Util.OS_FILE_DELIM + "output.txt";
	public static final String COMMA_FILE_PATH = "inputFiles"+ Util.OS_FILE_DELIM + "comma.txt";
	public static final String PIPE_FILE_PATH = "inputFiles"+ Util.OS_FILE_DELIM + "pipe.txt";
	public static final String SPACE_FILE_PATH = "inputFiles"+ Util.OS_FILE_DELIM + "space.txt"; 

	public static void main(String[] args){
		//Get list of people from files
		DataFileReader<Person> fileReader = new DataFileReader<Person>();
		Collection<Person> people = fileReader.getFileData(Person.class, PIPE_FILE_PATH);
		people.addAll(fileReader.getFileData(Person.class, COMMA_FILE_PATH));
		people.addAll(fileReader.getFileData(Person.class, SPACE_FILE_PATH));

		//Obtain output object
		DataOutput output = null;

		try{
			output = new DataFileOutput(OUTPUT_FILE_PATH);
		}catch (Exception e){
			Util.handleException(e, "Exception trying to open FileOutput object");
			output = new DataSystemOutput();
		}

		//Use TreeSet to sort with Comparator on instantiation; then output
		//First output sort by gender, then last name
		TreeSet<Person> ts = new TreeSet<Person>(new GenderLastNamePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 1:",ts.iterator());
		//Second output sort by date of birth
		ts = new TreeSet<Person>(new DatePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 2:",ts.iterator());
		//Third output sort by last name, descending
		ts = new TreeSet<Person>(new LastNamePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 3:",ts.descendingIterator());

		//Close file output streams if using file output
		if(output instanceof DataFileOutput)
			((DataFileOutput) output).close();
	}
}
