package codetest.java.rich.marscher.file.input.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.model.Gender;
import codetest.java.rich.marscher.model.Person;

/**
 * Concrete Implementation of PersonFileParser
 * for parsing Person objects written in the Space DataFileFormat
 */
public class PersonSpaceFileParser extends PersonFileParser {

	//Expected format of the date in space-separated file
	public static final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
	
	PersonSpaceFileParser(DataFileFormat format) {
		super(format);
	}
	
	/**
	 * Attempts to parse a Person object from a space-separated String.
	 * Expects each line in format: LastName FirstName MiddleInitial Gender MM-DD-YYYY Color
	 * @return a Collection of Person objects parsed out of the String 'line'.
	 * @throws InvalidDataLineException if the line cannot be parsed via the expected format
	 */
	@Override
	public Person readObject(String line) throws InvalidDataLineException {
		//Parse line of text
		String[] data = line.split(getFormat().getTokenRegex());

		//Expecting LastName FirstName MiddleInitial Gender MM-DD-YYYY Color
		if(data.length != 6){
			throw new InvalidDataLineException("Length incorrect");
		}

		Person person = new Person();

		person.setLastName(data[0]);
		person.setFirstName(data[1]);
		person.setMiddleName(data[2]);
		person.setGender(Gender.resolveGender(data[3]));
		
		try {
			person.setDateOfBirth(df.parse(data[4]));
		} catch (ParseException e) {
			throw new InvalidDataLineException("Date of Birth did not match expected format");
		}
		
		person.setFavoriteColor(data[5]);
		
		return person;
	}
}