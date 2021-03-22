package com.inditex.inditex.exceptions;

public class OrderExceptions extends RuntimeException {
    public OrderExceptions(Long id){
        super("No order availbale with Id: " + id);
    }
}
