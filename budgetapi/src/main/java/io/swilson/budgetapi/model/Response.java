package io.swilson.budgetapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

// This stuff will be in all responses we send after requests in our app
@Data
@SuperBuilder //use the builder pattern
@JsonInclude(JsonInclude.Include.NON_NULL) //when one attr is null, dont show it to the user
public class Response {
    protected LocalDateTime timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;
}
