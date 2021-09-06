package com.example.demo.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UnAuthorizeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3685854100023657682L;

	private final String errorMessage;

    private final HttpStatus errorStatus;

    public UnAuthorizeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorStatus = HttpStatus.UNAUTHORIZED;
    }

    public UnAuthorizeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
        this.errorStatus = HttpStatus.UNAUTHORIZED;
    }
}
