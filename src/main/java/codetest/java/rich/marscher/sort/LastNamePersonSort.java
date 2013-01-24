package codetest.java.rich.marscher.sort;

import codetest.java.rich.marscher.Util;
import codetest.java.rich.marscher.model.Person;

public class LastNamePersonSort extends DataObjectSort<Person> {

	@Override
	/**
	 * Assumes p1 and p2 are not null
	 * Returns BEFORE if p1's last name is lexicographically before p2's. 
	 * Nulls are considered the last in the lexicographical order 
	 */
	public int compare(Person p1, Person p2) {
		if(Util.matchEqualsOrNulls(p1.getLastName(), p2.getLastName())){
			return BEFORE;
		} else if(p1.getLastName() == null){
			return AFTER;
		} else if (p2.getLastName() == null){
			return BEFORE;
		}

		return p1.getLastName().compareTo(p2.getLastName()) <0 ? BEFORE : AFTER;
	}

}
