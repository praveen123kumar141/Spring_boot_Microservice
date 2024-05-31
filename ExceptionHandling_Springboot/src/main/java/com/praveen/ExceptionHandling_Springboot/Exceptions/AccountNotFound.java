package com.praveen.ExceptionHandling_Springboot.Exceptions;

import java.util.Date;
import java.util.List;

public class AccountNotFound extends Exception{
    private String errorMessage;
    private int errorCode;
    private Date timeStamp;

    public AccountNotFound(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


}
