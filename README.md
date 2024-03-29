aws-helper
============
<a href="https://travis-ci.org/davidmoten/aws-helper"><img src="https://travis-ci.org/davidmoten/aws-helper.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/aws-helper/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/aws-helper)<br/>
[![codecov](https://codecov.io/gh/davidmoten/aws-helper/branch/master/graph/badge.svg)](https://codecov.io/gh/davidmoten/aws-helper)

Utilities to add type-safety in Java AWS Lambdas with API Gateway.

## Getting started
Add this dependency to your pom.xml. It has no transitive dependencies.
```xml
<dependency>
    <groupId>com.github.davidmoten</groupId>
    <artifactId>aws-helper</artifactId>
    <version>VERSION_HERE</version>
</dependency>
```

## StandardRequestBodyPassThrough.from
When a request is mapped through from a Gateway API to a Lambda the *pass-through* json body template looks like [this](src/docs/pass-through-body-mapping-template.txt).

When you want to deal with the body in a java Lambda navigating this bit of json is painful because of null checks, nested maps and type coercion. This is where `StandardRequestBodyPassThrough` helps out:

```java
public class RequestHandler {

    public String getResult(Map<String, Object> input, Context context) {
        LambdaLogger log = context.getLogger();
        
        // expects full request body passthrough from api gateway integration request
        StandardRequestBodyPassThrough request = StandardRequestBodyPassThrough.from(input);
        
        //get info from input in a type-safe way
        log.debug("http-request=" + request.httpRequest().orElse(""));
        log.debug("user=" + request.user().orElse(""));
        log.debug("api-id=" + request.apiId().orElse(""));
        log.debug("source-ip="+ request.sourceIp().orElse(""));
        ...
    }
}       
```
