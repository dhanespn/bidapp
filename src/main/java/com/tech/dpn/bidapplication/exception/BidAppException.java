package com.tech.dpn.bidapplication.exception;

public class BidAppException extends Exception {
    public BidAppException(String message) {
        super(message);
    }

    public BidAppException(String message, Exception ex) {
        super(message, ex);
    }
}
