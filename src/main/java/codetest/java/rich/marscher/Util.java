package codetest.java.rich.marscher;


public class Util {

	//Operating System specific file properties
	public static final String OS_FILE_DELIM = System.getProperty("file.separator");
	public static final String OS_FILE_NEW_LINE = System.getProperty("line.separator");

	/**
	 * Prints a stack trace of the exception and any amount of optional, contextual Strings
	 */
	public static final void handleException(Throwable e, String... debugText){
		if(debugText != null){
			for(String s : debugText){
				System.out.println(s);
			}
		}

		e.printStackTrace();
	}

	/**
	 * True if both arguments are null or equal to one another. (Exepects the equals method to be implemented)
	 */
	public static final boolean matchEqualsOrNulls(Object a, Object b){
		if(a == null && b == null){
			return true;
		}else if(a == null || b == null){
			return false;
		}else if(a.equals(b)){
			return true;
		}

		return false;
	}
}
