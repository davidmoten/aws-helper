package com.github.davidmoten.aws.helper;

public final class RedirectException extends RuntimeException {

    private static final long serialVersionUID = -410977256337125533L;

    public RedirectException(String url) {
        super(url);
    }

}
