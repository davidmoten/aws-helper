package com.github.davidmoten.aws.helper;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class StandardRequestBodyPassThrough {

    private final Map<String, Object> map;
    private final Map<String, String> context;
    private final Map<String, String> headers;

    @SuppressWarnings("unchecked")
    private StandardRequestBodyPassThrough(Map<String, Object> m) {
        this.map = m == null ? Collections.emptyMap() : m;
        this.context = (Map<String, String>) map.get("context");
        this.headers = getHeaders(map);
    }

    public static StandardRequestBodyPassThrough from(Map<String, Object> input) {
        return new StandardRequestBodyPassThrough(input);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> params(Map<String, Object> map) {
        Map<String, Object> m = (Map<String, Object>) ((Map<String, Object>) map.get("params"));
        if (m == null)
            return Collections.emptyMap();
        else
            return m;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getHeaders(Map<String, Object> map) {
        Map<String, String> m = (Map<String, String>) params(map).get("header");
        if (m == null)
            return Collections.emptyMap();
        else {
            return m.entrySet().stream().collect(Collectors.toMap(x -> x.getKey().toLowerCase(),
                    x -> x.getValue()));
        }
    }

    public Map<String, String> headers() {
        return headers;
    }

    public Optional<String> header(String name) {
        return Optional.ofNullable(headers.get(name.toLowerCase()));
    }


    @SuppressWarnings("unchecked")
    public Map<String, String> stageVariables() {
        Map<String, String> m = (Map<String, String>) map.get("stage-variables");
        if (m == null)
            return Collections.emptyMap();
        else
            return m;
    }

    @Override
    public String toString() {
        return String.valueOf(map);
    }

    private Optional<String> c(String key) {
        if (context == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(context.get(key));
        }
    }

    public Optional<String> accountId() {
        return c("account-id");
    }

    public Optional<String> apiId() {
        return c("api-id");
    }

    public Optional<String> apiKey() {
        return c("api-key");
    }

    public Optional<String> authorizerPrincipalId() {
        return c("authorizer-principal-id");
    }

    public Optional<String> caller() {
        return c("caller");
    }

    public Optional<String> cognitoAuthenticationProvider() {
        return c("cognito-authentication-provider");
    }

    public Optional<String> cognitoAuthenticationType() {
        return c("cognito-authentication-type");
    }

    public Optional<String> cognitoIdentityPoolId() {
        return c("cognito-identity-pool-id");
    }

    public Optional<String> httpMethod() {
        return c("http-method");
    }

    public Optional<String> stage() {
        return c("stage");
    }

    public Optional<String> sourceIp() {
        return c("source-ip");
    }

    public Optional<String> user() {
        return c("user");
    }

    public Optional<String> userArn() {
        return c("user-arn");
    }

    public Optional<String> requestId() {
        return c("request-id");
    }

    public Optional<String> resourceId() {
        return c("resource-id");
    }

    public Optional<String> resourcePath() {
        return c("resource-path");
    }

    public Optional<String> stageVariables(String key) {
        return Optional.ofNullable(stageVariables().get(key));
    }

    public Optional<String> queryStringParameter(String name) {
        @SuppressWarnings("unchecked")
        Map<String, String> m = (Map<String, String>) params(map).get("querystring");
        if (m == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(m.get(name));
        }
    }

    // {body-json={}, params={path={}, querystring={boo=2, hello=true},
    // header={Accept=*/*, CloudFront-Forwarded-Proto=https,
    // CloudFront-Is-Desktop-Viewer=true, CloudFront-Is-Mobile-Viewer=false,
    // CloudFront-Is-SmartTV-Viewer=false,
    // CloudFront-Is-Tablet-Viewer=false, CloudFront-Viewer-Country=AU,
    // Host=w0ik4qs1c7.execute-api.ap-southeast-2.amazonaws.com,
    // User-Agent=curl/7.35.0, Via=1.1
    // 5672636208e8fb405fd765eca1216f8a.cloudfront.net (CloudFront),
    // X-Amz-Cf-Id=bp5msOV06nJe1Ow27e6OMxRHnRcsMXgrRGoKW9cRgsg==,
    // x-api-key=W,
    // X-Forwarded-For=60.242.1,23, 54.24.12.34 X-Forwarded-Port=443,
    // X-Forwarded-Proto=https}},
    // stage-variables={function=get-craftpic-dev}, context={account-id=,
    // api-id=w0ik4qs1c7, api-key=Wc
    // authorizer-principal-id=, caller=, cognito-authentication-provider=,
    // cognito-authentication-type=, cognito-identity-id=,
    // cognito-identity-pool-id=, http-method=GET, stage=dev,
    // source-ip=60.242.107.227, user=, user-agent=curl/7.35.0, user-arn=,
    // request-id=8501b909-b7f2-11e6-b832-dhjkjhkjc3e75e7, resource-id=a10fd5,
    // resource-path=/check}}

}
