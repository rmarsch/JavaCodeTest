package codetest.java.rich.marscher.file.input.format;

/**
 * Defined separately so they are available in the DataFileFormat enum constructor calls
 */
public class DataFileFormatRegex {
	public static final String COMMA_DETECTION_REGEX = "^[\\w]+[, ]{2}.*$";
	public static final String COMMA_TOKEN_REGEX = "[ ]?,[ ]?";
	public static final String PIPE_DETECTION_REGEX = "^[\\w]+[ |]{3}.*$";
	public static final String PIPE_TOKEN_REGEX = "[ ]?\\|[ ]?";
	public static final String SPACE_DETECTION_REGEX = "^[\\w]+[ ]{1}[\\w]+.*$";
	public static final String SPACE_TOKEN_REGEX = "[ ]+";
}
