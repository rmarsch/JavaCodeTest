package codetest.test.rich.marscher.file.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import org.junit.Test;

import codetest.java.rich.marscher.CodeTest;
import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.file.output.DataFileOutput;
import codetest.java.rich.marscher.file.output.DataOutput;
import codetest.java.rich.marscher.file.output.DataSystemOutput;
import codetest.java.rich.marscher.model.Person;
import codetest.java.rich.marscher.sort.DatePersonSort;
import codetest.java.rich.marscher.sort.GenderLastNamePersonSort;
import codetest.java.rich.marscher.sort.LastNamePersonSort;
import codetest.test.rich.marscher.file.AbstractFileTest;

/**
 * Roll DataFileOutput and DataSystemOutput into one test since cases are small
 * and to test the fall back from file to system
 */
public class DataOutputTest extends AbstractFileTest {

	@Test
	public void testOutputFiles(){
		DataOutput output = null;
		Exception outException = null;

		//Test fallback to DataSystemOutput when an invalid output file is passed to DataFileOutput
		try{
			output = new DataFileOutput(INVALID_OUTPUT_FILE);
		}catch (Exception e){
			outException = e;
			output = new DataSystemOutput();
		}

		assertNotNull(outException);
		assertTrue(output instanceof DataSystemOutput);

		//Test case for a valid output file
		outException = null;

		try{
			output = new DataFileOutput(CodeTest.OUTPUT_FILE_PATH);
		}catch (Exception e){
			outException = e;
			output = new DataSystemOutput();
		}

		assertNull(outException);
		assertTrue(output instanceof DataFileOutput);

		Collection<Person> people = new ArrayList<Person>();
		people.addAll(commaPeople);
		people.addAll(pipePeople);
		people.addAll(spacePeople);

		//Test the expected output
		//Use TreeSet to sort with Comparator on instantiation; then output
		TreeSet<Person> ts = new TreeSet<Person>(new GenderLastNamePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 1:",ts.iterator());

		ts = new TreeSet<Person>(new DatePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 2:",ts.iterator());

		ts = new TreeSet<Person>(new LastNamePersonSort());
		ts.addAll(people);
		output.printDataObjects("Output 3:",ts.descendingIterator());

		//Close file output streams if using file output
		if(output instanceof DataFileOutput)
			((DataFileOutput) output).close();

		//Confirm match on the output to expected output
		BufferedReader in = null, expectedIn = null;

		try{

			in = new BufferedReader(new FileReader(outputFile));
			expectedIn = new BufferedReader(new FileReader(expectedOutputFile));

			String line = null;

			while((line = expectedIn.readLine()) != null){
				String lineUnderTest = in.readLine();

				//Line should exist of the expectedIn line did
				assertNotNull(lineUnderTest);
				assertEquals(line, lineUnderTest);
			}

			//Since the expected output ended, so should the one under test
			assertNull(in.readLine());

		}catch(Exception e){
			Util.handleException(e,"Unexpected exception testing matching output");
		}finally{
			closeReader(in);
			closeReader(expectedIn);
		}

	}
	
	private void closeReader(Reader s){
		if(s != null){
			try{
				s.close();
			}catch(IOException e){
				Util.handleException(e, "Critically failed closing input stream");
			}
		}
	}

}
