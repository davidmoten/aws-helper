package com.github.davidmoten.aws.helper;

class Util {

    static String cleanExceptionMessage(String message) {
        // need to do this for Lambda Api Gateway non-proxy integrations
        // because the existence of a newline character in the message
        // buggers up the returned json (is invalid json). In addition
        // the apparent bug in AWS lambda error handling also seems to extend
        // to treatment of \\n in the error message which should come through
        // as \n but is \\n.
        // TODO raise bug report with AWS
        // TODO do we need to worry about newlines in error messages further down the
        // callstack (cause)?
        return message.replace("\n", "\\n");
    }
}
