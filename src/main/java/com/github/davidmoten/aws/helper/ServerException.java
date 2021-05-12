package com.github.davidmoten.aws.helper;

public final class ServerException extends RuntimeException{

    private static final long serialVersionUID = -637244162148489596L;
    
    public ServerException(String message, Throwable e) {
        super("ServerException: " + message, e);
    }
    
    public ServerException(String message) {
        this(message, null);
    }
    
    public ServerException(Throwable e) {
        this(e.getMessage(), e);
    }
}
