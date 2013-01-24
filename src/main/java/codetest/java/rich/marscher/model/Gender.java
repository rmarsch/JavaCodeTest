package codetest.java.rich.marscher.model;

/**
 * Enum type representing Gender types
 * Interprets a data string as a possible Gender type
 * based on regex.
 * Implements comparison where female is before male.
 *
 */
public enum Gender {
	MALE("Male"),
	FEMALE("Female");

	//Regex for accepted gender values
	private static final String MALE_REGEX = "^male|[m]$";
	private static final String FEMALE_REGEX = "^female|[f]$";

	private final String genderTitle;


	Gender(String s){
		genderTitle = s;
	}

	public String getGenderTitle(){
		return genderTitle;
	}

	/**
	 * Accepted:: Male <- Male, M  :  Female <- Female, F 
	 * @param input accepted gender string representation to be interpreted
	 * @return Gender enum if case-insensitive match, or null if no match to the input is found
	 */
	public static Gender resolveGender(String input){
		if(input == null){
			return null;
		}

		if(input.toLowerCase().matches(MALE_REGEX)){
			return MALE;
		}else if(input.toLowerCase().matches(FEMALE_REGEX)){
			return FEMALE;
		}

		return null;
	}

	/**
	 * Compare Gender objects:
	 * Female < Male
	 */
	public int compareWith(Gender g){
		if(g == null){
			return -1;
		}

		if(genderTitle.equals(FEMALE.getGenderTitle()) && g.getGenderTitle().equals(FEMALE.getGenderTitle())){
			return 0;
		}else if(genderTitle.equals(FEMALE.getGenderTitle())){
			return -1;
		}else{
			return 1;
		}
	}
}
