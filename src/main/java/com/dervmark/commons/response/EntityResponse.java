package com.dervmark.commons.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntityResponse<T> {
    
    private boolean 		success;
    private String 			message;
    private T 				entity;
    private int 			code;
    private LocalDateTime 	timestamp = LocalDateTime.now();
    private List<String> 	errors;

    public EntityResponse(boolean success, String message, T entity, int code) {
        this.success = success;
        this.message = message;
        this.entity = entity;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public EntityResponse(boolean success, String message, T entity, int code, List<String> errors) {
        this.success = success;
        this.message = message;
        this.entity = entity;
        this.code = code;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}