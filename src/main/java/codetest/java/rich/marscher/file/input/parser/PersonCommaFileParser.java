package codetest.java.rich.marscher.file.input.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.model.Gender;
import codetest.java.rich.marscher.model.Person;

/**
 * Concrete Implementation of PersonFileParser
 * for parsing Person objects written in the Comma DataFileFormat
 */
public class PersonCommaFileParser extends PersonFileParser {
	
	public static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	PersonCommaFileParser(DataFileFormat format) {
		super(format);
	}
	
	/**
	 * Attempts to parse a Person object from a comma-separated String.
	 * Expects each line in format: Last,First,Gender,Color,MM/DD/YYYY
	 * @return a Collection of Person objects parsed out of the String 'line'.
	 * @throws InvalidDataLineException if the line cannot be parsed via the expected format
	 */
	@Override
	public Person readObject(String line) throws InvalidDataLineException {
		String[] data = line.split(getFormat().getTokenRegex());

		//Expecting Last,First,Gender,Color,MM/DD/YYYY
		if(data.length != 5){
			throw new InvalidDataLineException("Length incorrect");
		}

		Person person = new Person();

		person.setLastName(data[0]);
		person.setFirstName(data[1]);
		person.setGender(Gender.resolveGender(data[2]));
		person.setFavoriteColor(data[3]);
		
		try {
			person.setDateOfBirth(df.parse(data[4]));
		} catch (ParseException e) {
			throw new InvalidDataLineException("Date of Birth did not match expected format");
		}
		
		return person;
	}
	
}
