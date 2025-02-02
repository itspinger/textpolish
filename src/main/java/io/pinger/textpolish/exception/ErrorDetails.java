package io.pinger.textpolish.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents an error message that will be returned if an error occurred
 * while executing a request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class ErrorDetails {
    private ErrorCode errorCode;
    private String errorMessage;
    private Instant timestamp;
    private Map<String, String> extra;

    public ErrorDetails(ErrorCode errorCode, String errorMessage, Instant timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public ErrorDetails(ErrorCode errorCode, String errorMessage, Instant timestamp, Map<String, String> extra) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
        this.extra = extra;
    }
}
