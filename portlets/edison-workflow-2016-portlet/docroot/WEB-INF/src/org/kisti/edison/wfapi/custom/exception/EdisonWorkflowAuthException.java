package org.kisti.edison.wfapi.custom.exception;

public class EdisonWorkflowAuthException extends Exception{
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public EdisonWorkflowAuthException(){
        super();
        this.errorMessage = "User Authentication Error";
    }
    
    public EdisonWorkflowAuthException(String errorMessage){
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage(){
        return this.errorMessage;
    }

}
