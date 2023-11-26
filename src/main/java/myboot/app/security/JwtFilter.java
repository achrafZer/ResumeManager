package myboot.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A filter responsible for extracting the JWT (JSON Web Token) from the HTTP request headers.
 * It verifies the token if present, and sets up the Spring Security SecurityContext accordingly.
 * This filter ensures that each HTTP request is checked for a valid JWT for authentication and authorization.
 */
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtTokenProvider;

    /**
     * Constructor for JwtFilter.
     *
     * @param jwtTokenProvider Provider for JWT-related operations such as token validation and parsing.
     */
    public JwtFilter(JwtProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        System.out.println("Init JWT filter");
    }

    /**
     * Internal filter method that checks for the presence of JWT in the request,
     * validates it, and sets the authentication in the security context.
     *
     * @param httpServletRequest  The request object containing the HTTP request data.
     * @param httpServletResponse The response object for sending back HTTP responses.
     * @param filterChain         The filter chain to pass the request and response after processing.
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (MyJwtException ex) {
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
