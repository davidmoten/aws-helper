# aws-helper
Utilities to add type-safety in Java AWS Lambdas with API Gateway.


#StandardRequestBodyPassThrough.from
When a request is mapped through from an API to a Lambda the *pass-through* json body template looks like [this](src/docs/pass-through-body-mapping-template.txt).

When you want to deal with the body in a java Lambda navigating this bit of json is fraught with difficulties of nested maps that may or may not be present and the possibility of spellling mistakes. 

For these reasons you can do this in a java Lambda:

```java
public class RequestHandler {

    public String getResult(Map<String, Object> input, Context context) {
        LambdaLogger log = context.getLogger();
        
        // expects full request body passthrough from api gateway integration request
        StandardRequestBodyPassThrough request = StandardRequestBodyPassThrough.from(input);
        
        log.debug("http-request=" + request.httpRequest());
        ...
    }
}       
```
