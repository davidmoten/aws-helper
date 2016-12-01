package com.github.davidmoten.aws.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(r.headers().isEmpty());
    }

    @Test
    public void testEmptyParams() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        map.put("params", params);
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(map);
        assertFalse(r.queryStringParameter("a").isPresent());
    }

    @Test
    public void testEmptyHeaders() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        map.put("params", params);
        params.put("header", new HashMap<>());
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(map);
        assertFalse(r.header("a").isPresent());
    }

}
