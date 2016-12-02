package com.github.davidmoten.aws.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
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
    public void testEmptyStageVariables() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> m = new HashMap<>();
        map.put("stage.variables", m);
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(map);
        assertFalse(r.stageVariables("a").isPresent());
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
    
    @Test
    public void testHeaderExists() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        map.put("params", params);
        Map<String, String> headers = new HashMap<>();
        headers.put("a", "thevalue");
        params.put("header", headers);
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(map);
        assertTrue(r.header("a").isPresent());
        assertEquals("thevalue",r.header("a").get());
    }
    
    @Test
    public void testQueryParametersExist() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        map.put("params", params);
        Map<String, String> m = new HashMap<>();
        m.put("a", "thevalue");
        params.put("querystring", m);
        StandardRequestBodyPassThrough r = StandardRequestBodyPassThrough.from(map);
        assertTrue(r.queryStringParameter("a").isPresent());
        assertEquals("thevalue",r.queryStringParameter("a").get());
    }

}
