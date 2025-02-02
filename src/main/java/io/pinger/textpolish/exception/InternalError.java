package io.pinger.textpolish.exception;

import org.springframework.http.HttpStatus;

public class InternalError extends CustomException {

    public InternalError() {
        super("Internal error", ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
