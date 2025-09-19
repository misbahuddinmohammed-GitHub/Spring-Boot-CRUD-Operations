package jsp.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.springboot.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Failure");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordsFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoRecordsFoundException(NoRecordsFoundException exception)
	{
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Failure");
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
