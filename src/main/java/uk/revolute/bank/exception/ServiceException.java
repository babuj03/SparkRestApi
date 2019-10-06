package uk.revolute.bank.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */

@JsonIgnoreProperties(value = "stackTrace")
public class ServiceException extends Exception {

    private String errorMessage;

    public ServiceException(){ }
    public ServiceException( String errorMessage)    {
        super(errorMessage);
        this.errorMessage=errorMessage;

    }
}
