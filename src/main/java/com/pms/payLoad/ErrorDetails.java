package com.pms.payLoad;



import java.util.Date;

public class ErrorDetails {

    private String message;
    private Date date;
    private String uri;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ErrorDetails(String message, Date date, String uri) {
        this.message = message;
        this.date = date;
        this.uri = uri;
    }

}

