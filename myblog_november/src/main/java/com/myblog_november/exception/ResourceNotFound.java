package com.myblog_november.exception;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String s)
    {
        super(s);
    }
}
