package codetest.java.rich.marscher.sort;

import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.model.Gender;
import codetest.java.rich.marscher.model.Person;

public class GenderLastNamePersonSort extends DataObjectSort<Person> {

	@Override
	/**
	 * Assumes p1 and p2 are not null.
	 * Returns a BEFORE(-1) or AFTER(1) sort ranking based on the following rules:
	 * 1. Female is BEFORE Male or null
	 * 2. If gender matches, a last name lexicographically before the other (or other is null) is BEFORE
	 * 3. Otherwise AFTER
	 */
	public int compare(Person p1, Person p2) {
		Gender gender1 = p1.getGender(), gender2 = p2.getGender();

		if(Util.matchEqualsOrNulls(gender1, gender2)){
			LastNamePersonSort lns = new LastNamePersonSort();

			return lns.compare(p1, p2);
		}else if(gender1 != null 
				&& (gender1.compareWith(gender2) == BEFORE)){
			return BEFORE;
		}else{
			return AFTER;
		}
	}

}
