package din.tzustudy.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;

@ControllerAdvice
public class ResponseStatusExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusExceptionHandler(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());

    }
}
