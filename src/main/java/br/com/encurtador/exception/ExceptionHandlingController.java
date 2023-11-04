package br.com.encurtador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.encurtador.dto.ErrorInfo;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  @ExceptionHandler({NotFoundException.class})
	  @ResponseBody 
	  public ErrorInfo handleNotFoundException(HttpServletRequest req, NotFoundException e) {
	    return new ErrorInfo(req.getRequestURL().toString(), e);
	  }
	
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler({GenericException.class})
	  @ResponseBody 
	  public ErrorInfo handleGenericException(HttpServletRequest req, GenericException e) {
	    return new ErrorInfo(req.getRequestURL().toString(), e);
	  }
	
}
