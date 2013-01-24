package codetest.java.rich.marscher.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import codetest.java.rich.marscher.Util;

/**
 * DataObject implementation representing a Person
 * object as conceived in the model_output.txt
 * and inputFiles
 * Stores lastName, firstName, middleName, gender, 
 * favorite color, and date of birth.
 */
public class Person implements DataObject {

	//Standardized date output format (still need to strip leading 0s)
	private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	private static final String DATE_SEPARATOR = "/";

	private String lastName = null;
	private String firstName = null;
	private String middleName = null;
	private Gender gender = null;
	private String favoriteColor = null;
	private Date dateOfBirth = null;

	public Person(){ ; }

	/**
	 * Initializes the Person object with the argument values. Genders can be any accepted gender string (see Gender enum)
	 * and dateOfBirth should be a string of format MM/dd/yyyy
	 * @throws ParseException if the date of birth string does not match the MM-dd-yyyy format
	 */
	public Person(String lastName, String firstName, String middleName, String gender, String favoriteColor, String dateOfBirth)
	throws ParseException {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.gender = Gender.resolveGender(gender);
		this.favoriteColor = favoriteColor;

		if(dateOfBirth != null){
			this.dateOfBirth = df.parse(dateOfBirth);
		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFavoriteColor() {
		return favoriteColor;
	}
	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public boolean equals(Object o){
		if(o instanceof Person){
			Person tmp = (Person) o;

			if(Util.matchEqualsOrNulls(firstName, tmp.getFirstName())
					&& Util.matchEqualsOrNulls(lastName, tmp.getLastName())
					&& Util.matchEqualsOrNulls(middleName, tmp.getMiddleName())
					&& Util.matchEqualsOrNulls(gender, tmp.getGender())
					&& Util.matchEqualsOrNulls(favoriteColor, tmp.getFavoriteColor())
					&& Util.matchEqualsOrNulls(dateOfBirth, tmp.getDateOfBirth())
			){
				return true;
			}
		}

		return false;
	}

	public int hashCode(){
		int hash = 0;
		hash += firstName != null ? firstName.hashCode() : 0;
		hash += lastName != null ? lastName.hashCode() : 0;
		hash += dateOfBirth != null ? dateOfBirth.hashCode() : 0;
		hash += favoriteColor != null ? favoriteColor.hashCode() : 0;

		return hash;
	}

	/** 
	 * Outputs accepted output format of a Person object.
	 * The optional Middle Initial field is omitted. Use getter for Middle Initial.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();

		sb.append(lastName);
		sb.append(" ");
		sb.append(firstName);
		sb.append(" ");
		sb.append(gender != null ? gender.getGenderTitle() : "");
		sb.append(" ");
		sb.append(dateOfBirth != null ? stripDateLeadingZeros(df.format(dateOfBirth)) : "");
		sb.append(" ");
		sb.append(favoriteColor);

		return sb.toString();
	}


	/**
	 * 
	 * @param input date string in the format of the DateFormat (particularly the separator)
	 * @return the date string without leading zeroes
	 */
	private static final String stripDateLeadingZeros(String input){
		//Filters a little bit of already valid outputs or nulls
		if(input == null || !input.contains("0")){
			return input;
		}

		StringBuilder sb = new StringBuilder();

		String[] split = input.split(DATE_SEPARATOR);

		if(split.length != 3){
			return input;
		}

		if(split[0].startsWith("0")){
			sb.append(split[0].substring(1));
		}else{
			sb.append(split[0]);
		}

		sb.append(DATE_SEPARATOR);

		if(split[1].startsWith("0")){
			sb.append(split[1].substring(1));
		}else{
			sb.append(split[1]);
		}

		sb.append(DATE_SEPARATOR);

		sb.append(split[2]);

		return sb.toString();
	}

	@Override
	public String toDataString() {
		return toString();
	}
}
