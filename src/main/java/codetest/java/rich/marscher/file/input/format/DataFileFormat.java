package codetest.java.rich.marscher.file.input.format;

/**
 * Enum providing static resolution of supported DataFileFormats comprised of
 * a detection regex for detecting if the format of a file is expected to qualify for this format
 * and a token regex representing the accepted separator format of the data fields
 */
public enum DataFileFormat {
	
	COMMA_DATA_FILE_FORMAT(DataFileFormatRegex.COMMA_DETECTION_REGEX,DataFileFormatRegex.COMMA_TOKEN_REGEX),
	PIPE_DATA_FILE_FORMAT(DataFileFormatRegex.PIPE_DETECTION_REGEX,DataFileFormatRegex.PIPE_TOKEN_REGEX),
	SPACE_DATA_FILE_FORMAT(DataFileFormatRegex.SPACE_DETECTION_REGEX,DataFileFormatRegex.SPACE_TOKEN_REGEX);

	private String detectionRegex;
	private String tokenRegex;
	
	private DataFileFormat(String detectionRegex, String tokenRegex){
		this.detectionRegex = detectionRegex;
		this.tokenRegex = tokenRegex;
	}
	
	/**
	 * Get the Accepted Token Separator format for this DataFileFormat
	 * @return
	 */
	public String getTokenRegex(){
		return tokenRegex;
	}

	/**
	 * Automated detection of the DataFileFormat of a file based on a sample line from the file (usually the first).
	 * @param line - a sample line of data from the file (usually the first line)
	 * @return The detected DataFileFormat of the file
	 * @throws UnsupportedDataFileFormatException when a DataFileFormat cannot be detected / is not supported
	 */
	public static DataFileFormat resolveDataFileFormat(String line) throws UnsupportedDataFileFormatException {
		if(fileConformsToFormat(COMMA_DATA_FILE_FORMAT, line)){
            return COMMA_DATA_FILE_FORMAT;
        } else if(fileConformsToFormat(PIPE_DATA_FILE_FORMAT, line)){
            return PIPE_DATA_FILE_FORMAT;
        } else if(fileConformsToFormat(SPACE_DATA_FILE_FORMAT, line)){
            return SPACE_DATA_FILE_FORMAT;
        }
        
        throw new UnsupportedDataFileFormatException();
	}
	
	/**
	 * Helper method to test if a line of data conforms to the DataFileFormat
	 */
	private static boolean fileConformsToFormat(DataFileFormat format, String line){
		if(line != null){
			return line.matches(format.detectionRegex);
		}
		
		return false;
	}
}
