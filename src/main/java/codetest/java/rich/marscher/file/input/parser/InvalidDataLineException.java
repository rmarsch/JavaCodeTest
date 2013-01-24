package codetest.java.rich.marscher.file.input.parser;

public class InvalidDataLineException extends Exception {

	private static final long serialVersionUID = -1397688720721511081L;
	private String reason = "";
	
	public InvalidDataLineException(String reason){
		this.reason = reason;
	}

	@Override
	public String getMessage(){
		return reason;
	}
}
