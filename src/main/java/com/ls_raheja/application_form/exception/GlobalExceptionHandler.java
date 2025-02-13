package com.ls_raheja.application_form.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ls_raheja.application_form.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    
    @Override
    @SuppressWarnings("null")
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        // Creating a map to store error and it's value
        Map<String, String> validationErrors = new HashMap<>();

        // Getting the list of the errors
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        // Extracting each error key , value
        validationErrorList.forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    // Handle any exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest req) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // Handle file upload exception
    @ExceptionHandler(FileUploadExcpetion.class)
    public ResponseEntity<ErrorResponseDto> handleFileUploadException(Exception ex, WebRequest req) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ApplicantNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> 
    applicantNotFoundExp(ApplicantNotFoundException ex , WebRequest req) {

        ErrorResponseDto errorResponse = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                req.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        
    }

}
