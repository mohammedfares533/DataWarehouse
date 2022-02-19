package com.ProgressSoft.DataWarehouse.exception;


import com.ProgressSoft.DataWarehouse.configurations.ApplicationResourceBundle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * This Class for Fill Error Date To Customer
 */
@Setter
@Getter
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;


    /**
     * Constructor For Return Status and localized Massage
     *
     * @param httpStatus
     * @param message
     */
    public ApiException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = ApplicationResourceBundle.getString(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
