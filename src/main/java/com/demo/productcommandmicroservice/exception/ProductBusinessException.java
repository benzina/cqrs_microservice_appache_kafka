package com.demo.productcommandmicroservice.exception;

public class ProductBusinessException extends RuntimeException{
    public ProductBusinessException(String message,Long id){
        super(message+" "+id);
    }
}
