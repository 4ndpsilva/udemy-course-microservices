package com.udemy.creditappraiserms.exception;

import lombok.Getter;

public class CommunicationErrorException extends Exception{
    @Getter
    private Integer status;

    public CommunicationErrorException(String msg, Integer status){
        super(msg);
        this.status = status;
    }
}