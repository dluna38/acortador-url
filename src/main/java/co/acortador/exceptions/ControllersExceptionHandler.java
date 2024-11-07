package co.acortador.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<Object> fieldRequiredResponse(FieldRequiredException ex) {
        return ex.generateResponse();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundResponse(ResourceNotFoundException ex){
        return ex.generateResponse();
    }
    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<Object> unknownExceptionResponse(ResourceNotFoundException ex){
        return ex.generateResponse();
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> validationExceptionResponse(ValidationException ex){
        return ex.generateResponse();
    }

   /* @ExceptionHandler(SqlEx.class)
    public ResponseEntity<Object> sqlExceptionResponse(PSQLException ex){
        return new DatabaseHandleExceptions(ex).generateResponse();
    }*/

}
