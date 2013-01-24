package codetest.java.rich.marscher.sort;

import java.util.Date;

import codetest.java.rich.marscher.model.Person;


public class DatePersonSort extends DataObjectSort<Person> {

	@Override
	/**
	 * Assumes p1 and p2 are not null
	 * Compares p1's dateOfBirth to p2's
	 * If the p1 date of birth is equal to or before p2's, 
	 * last names are lexicographically compared and the result is returned
	 * (Added because the model_output takes this form, although the sort criteria didn't)
	 * Otherwise, AFTER(1) is returned.
	 * Nulls are considered after any valid date.
	 */
	public int compare(Person p1, Person p2) {
		Date p1Date = p1.getDateOfBirth();
		Date p2Date = p2.getDateOfBirth();

		if(p1Date != null && p2Date != null && p1Date.equals(p2Date)){
			/*Do last name check because matching dates in "model_output.txt" 
			are lexicographically sorted by last name */

			LastNamePersonSort lns = new LastNamePersonSort();

			return lns.compare(p1, p2);
		}else if(p1Date == null && p2Date == null){	
			return BEFORE;
		}
		else if(p1Date ==  null){
			return AFTER;
		} else if(p2Date == null || (p1Date.before(p2Date))){
			return BEFORE;
		} else{
			return AFTER;
		}
	}

}
