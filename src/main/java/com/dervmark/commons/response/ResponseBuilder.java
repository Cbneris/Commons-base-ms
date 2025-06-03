package com.dervmark.commons.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
	
	public static <T> ResponseEntity<EntityResponse<T>> success(String message, T data) {
        return buildResponse(true, message, data, HttpStatus.OK, null);
    }
	
    public static <T> ResponseEntity<EntityResponse<T>> created(String message, T data) {
        return buildResponse(true, message, data, HttpStatus.CREATED, null);
    }
    
    public static ResponseEntity<EntityResponse<Object>> notFound(String message) {
        // En caso de 404 no existe data y marcamos success=false
        return buildResponse(false, message, null, HttpStatus.NOT_FOUND, List.of(message));
    }
    
    public static ResponseEntity<EntityResponse<Object>> error(String message, HttpStatus status, List<String> errors) {
        return buildResponse(false, message, null, status, errors);
    }
    
    private static <T> ResponseEntity<EntityResponse<T>> buildResponse(
            boolean success,
            String 	message,
            T 		data,
            HttpStatus status,
            List<String> errors) {

        EntityResponse<T> response = new EntityResponse<>();
        response.setSuccess	(success);
        response.setMessage	(message);
        response.setEntity	(data);
        response.setCode	(status.value());
        response.setTimestamp(LocalDateTime.now());

        if (errors != null && !errors.isEmpty()) {
            response.setErrors(errors);
        }

        return ResponseEntity.status(status).body(response);
    }
    
}
