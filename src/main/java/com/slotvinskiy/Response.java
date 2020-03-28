package com.slotvinskiy;

public class Response {

    private String body;
    private Exception exception;
    private int responseCode;

    public String getBody() {
        return body;
    }

    public Exception getException() {
        return exception;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
