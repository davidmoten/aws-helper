package com.github.davidmoten.aws.helper;

public final class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1487063598262695807L;

    public BadRequestException(String message, Throwable e) {
        super("BadRequest: " + Util.cleanExceptionMessage(message), e);
    }
    
    public BadRequestException(String message) {
        this(message, null);
    }
    
    public BadRequestException(Throwable e) {
        this(e.getMessage(), e);
    }
    
}
