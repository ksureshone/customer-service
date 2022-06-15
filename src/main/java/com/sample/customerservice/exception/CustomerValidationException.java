package com.sample.customerservice.exception;

public class CustomerValidationException extends Exception {
    public CustomerValidationException(String msg){
        super(msg);
    }
}
