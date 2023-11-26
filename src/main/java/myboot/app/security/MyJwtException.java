package myboot.app.security;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling authentication-related problems.
 * This class extends the standard RuntimeException to include additional information
 * about the HTTP status code related to the JWT (JSON Web Token) authentication error.
 *
 * <p>Use this exception to indicate various types of JWT authentication failures,
 * such as token expiration, invalid token, or access denied issues.
 */
public class MyJwtException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // HTTP status associated with the JWT authentication error.
    private final HttpStatus httpStatus;

    /**
     * Constructs a new MyJwtException with the specified detail message and HTTP status.
     *
     * @param message    The detailed message (which is saved for later retrieval
     *                   by the Throwable.getMessage() method).
     * @param httpStatus The HTTP status code that should be associated with this
     *                   exception (usually representing an HTTP error response).
     */
    public MyJwtException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    /**
     * Retrieves the HTTP status code associated with this JWT exception.
     *
     * @return The HTTP status code that corresponds to the specific nature of the JWT exception.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
