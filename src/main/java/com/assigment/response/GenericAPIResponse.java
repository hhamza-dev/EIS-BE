package com.assigment.response;


public class GenericAPIResponse {

    private String responseCode;
    private Object responseData;
    private String responseMessage;
    private String errorMessageData;
    private String responseStatus;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getErrorMessageData() {
        return errorMessageData;
    }

    public void setErrorMessageData(String errorMessageData) {
        this.errorMessageData = errorMessageData;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
