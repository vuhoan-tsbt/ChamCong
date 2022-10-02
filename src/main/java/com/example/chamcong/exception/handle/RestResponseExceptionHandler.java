package com.example.chamcong.exception.handle;

import com.example.chamcong.exception.data.DataNotFoundException;
import com.example.chamcong.model.RootResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String message = ex.getAllErrors().stream().map(x -> x.getDefaultMessage()).findFirst().orElse("");
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(message);
        root.setCode(400);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleFailedDueToKnownException(DataNotFoundException ex, HttpServletRequest request) {
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(ex.getMessage());
        root.setCode(404);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleTokenTimeoutException(ExpiredJwtException ex) {
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(ex.getMessage());
        root.setCode(410);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handlePermissionDenied(AccessDeniedException ex) {
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(ex.getMessage());
        root.setCode(405);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(ex.getMessage());
        root.setCode(408);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(exc.getMessage());
        root.setCode(417);
        return new ResponseEntity<Object>(root, HttpStatus.OK);
    }
}
