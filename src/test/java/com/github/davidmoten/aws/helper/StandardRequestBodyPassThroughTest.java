package com.github.davidmoten.aws.helper;

import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class StandardRequestBodyPassThroughTest {

    @Test
    public void testNullInput() {
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(null);
        assertFalse(r.accountId().isPresent());
    }
    
    @Test
    public void testEmptyInput() {
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(Collections.emptyMap());
        assertFalse(r.accountId().isPresent());
        assertFalse(r.header("Some").isPresent());
        assertFalse(r.queryStringParameter("p").isPresent());
    }
    
    @Test
    public void test() {
        Map<String,Object> map = new HashMap<>();
        
    }
    

    
}
