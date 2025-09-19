package jsp.springboot.exception;

public class NoRecordsFoundException extends RuntimeException{
	public NoRecordsFoundException(String message)
	{
		super(message);
	}
}
