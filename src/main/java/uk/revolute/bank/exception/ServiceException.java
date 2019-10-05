package uk.revolute.bank.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "stackTrace")
public class ServiceException extends Exception {

    private String errorMessage;

    public ServiceException(){ }
    public ServiceException( String errorMessage)    {
        super(errorMessage);
        this.errorMessage=errorMessage;

    }
}
