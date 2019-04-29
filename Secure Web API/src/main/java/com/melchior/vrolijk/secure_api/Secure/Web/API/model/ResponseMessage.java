package com.melchior.vrolijk.secure_api.Secure.Web.API.model;

@SuppressWarnings("ALL")
public class ResponseMessage
{
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
