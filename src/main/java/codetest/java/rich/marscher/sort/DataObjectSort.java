package codetest.java.rich.marscher.sort;

import java.util.Comparator;

import codetest.java.rich.marscher.model.Person;

/**
 * 
 * Abstract Sort class for implementing Comparators for
 * DataObjects. Comparators allow TreeSets to sort
 * a type of Object by rules defined in the compare method.
 *
 * @param <T> Type of DataObject to compare
 */
public abstract class DataObjectSort<T> implements Comparator<T> {

	public static final int EQUALS = 0;
	public static final int BEFORE = -1;
	public static final int AFTER = 1;

	public abstract int compare(T p1, T p2);

}
