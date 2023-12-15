package com.myblog_november.payload;
import java.util.*;
public class ErrorDetails {


    private Date timestamp;

    private String messgae;

    private String details;

    public ErrorDetails(Date timestamp, String messgae, String details) {
        this.timestamp = timestamp;
        this.messgae = messgae;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
