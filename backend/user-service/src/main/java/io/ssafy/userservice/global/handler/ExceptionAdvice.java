package io.ssafy.userservice.global.handler;

import io.ssafy.userservice.global.exception.InvalidException;
import io.ssafy.userservice.global.exception.JwtTokenException;
import io.ssafy.userservice.global.exception.ServiceRuntimeException;
import io.ssafy.userservice.global.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static io.ssafy.userservice.global.response.Response.ERROR;


@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private ResponseEntity<Response<?>> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable, status), headers, status);
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<?> handleInvalidException(InvalidException e) {
        log.error("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        log.error("Unexpected service exception occurred: {}", e.getMessage(), e);
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/error")
    public ResponseEntity<?> notFoundException(NoHandlerFoundException e){
        return newResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<?> handleJwtTokenException(JwtTokenException e){
        return newResponse(e, HttpStatus.UNAUTHORIZED);
    }


}
