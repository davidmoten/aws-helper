package com.github.davidmoten.aws.helper;

public final class ServerException extends RuntimeException{

    private static final long serialVersionUID = -637244162148489596L;
    
    public ServerException(Throwable e) {
        super("ServerException: " + e.getMessage(), e);
    }
}
