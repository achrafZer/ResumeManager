package myboot.app5.security;

import org.springframework.http.HttpStatus;

/*
 * Une exception pour coder les problèmes d'authentification.
 */
public class MyJwtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	public MyJwtException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
