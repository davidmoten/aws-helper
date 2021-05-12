package com.github.davidmoten.aws.helper;

public final class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1487063598262695807L;

    public BadRequestException(Throwable e) {
        super("BadRequest: " + e.getMessage(), e);
    }
    
}
