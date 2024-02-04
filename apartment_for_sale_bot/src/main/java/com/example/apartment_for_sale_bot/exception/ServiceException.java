package com.example.apartment_for_sale_bot.exception;

public class ServiceException extends Exception {
    ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
