package com.github.davidmoten.aws.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExceptionsTest {

    @Test
    public void testServerExceptionPrefixAdded() {
        assertEquals("ServerException: hello", new ServerException("hello").getMessage());
    }
    
    @Test
    public void testBadRequestExceptionPrefixAdded() {
        assertEquals("BadRequest: hello", new BadRequestException("hello").getMessage());
    }

}
