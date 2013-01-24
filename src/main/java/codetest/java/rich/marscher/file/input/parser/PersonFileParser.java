package codetest.java.rich.marscher.file.input.parser;

import codetest.java.rich.marscher.file.input.format.DataFileFormat;
import codetest.java.rich.marscher.model.Person;

/**
 * Abstract class providing the structure for a hierarchy
 * of FileParsers to parse Person objects
 *
 */
public abstract class PersonFileParser extends FileParser<Person> {

	PersonFileParser(DataFileFormat format) {
		super(format);
	}

}
