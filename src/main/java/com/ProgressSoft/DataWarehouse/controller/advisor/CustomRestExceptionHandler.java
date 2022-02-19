package com.ProgressSoft.DataWarehouse.controller.advisor;

import com.ProgressSoft.DataWarehouse.controller.base.Controllable;
import com.ProgressSoft.DataWarehouse.dto.common.BaseResponse;
import com.ProgressSoft.DataWarehouse.dto.common.ErrorModel;
import com.ProgressSoft.DataWarehouse.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@ControllerAdvice
/**
 * Class Handle  Exceptions
 */
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String BAD_REQUEST_NAME = "BAD_REQUEST.MESSAGE";
    private static final String ERROR_MESSAGE_NAME = "ERROR.MESSAGE";
    private static final String NOT_READABLE_ERROR_NAME="NOT_READABLE_ERROR";

    /**
     * Handle Api  Exception
     *
     * @param error
     * @param request
     * @return
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<BaseResponse> handleCustomError(ApiException error, WebRequest request) {

        return new ResponseEntity<BaseResponse>(Controllable.preparedResponse(
                Controllable.preparedErrorResponse(error.getMessage()), ERROR_MESSAGE_NAME),
                error.getHttpStatus());
    }

    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        return new ResponseEntity<>(Controllable.preparedResponse(
                Controllable.preparedErrorResponse(ex.getLocalizedMessage()), NOT_READABLE_ERROR_NAME),
                status);
    }

    /**
     * Use This Method For Handle Not Valid User Arguments
     *
     * @param ex      : Represent Exception Object
     * @param headers : Represent Request Header
     * @param status  : Represent Response Status
     * @param request : Represent User Request
     * @return Custom Exception Massage To Till User Which Argument Is In valid
     */
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<ErrorModel> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(Controllable.preparedErrorResponse(error.getDefaultMessage()));
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(Controllable.preparedErrorResponse(error.getDefaultMessage()));
        }

        return new ResponseEntity<>(Controllable.preparedResponse(errors, BAD_REQUEST_NAME), HttpStatus.BAD_REQUEST);
    }
}
