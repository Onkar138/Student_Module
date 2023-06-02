package com.tnsif.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resource;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resource,String fieldName,long fieldValue){
        super(String.format("%s %s is Not Found: '%s'",resource,fieldName,fieldValue));
        this.resource=resource;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    public String getResource() {
        return resource;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
