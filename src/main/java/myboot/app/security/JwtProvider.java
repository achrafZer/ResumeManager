package myboot.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import myboot.app.dao.PersonRepository;
import myboot.app.model.XUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Provides utilities for creating, verifying, and managing JSON Web Tokens (JWT) for authentication.
 */
@Component
public class JwtProvider {

    /**
     * The secret key used for signing JWTs.
     * It's encoded in Base64 and initialized in the {@link #init()} method.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    /**
     * The expiration time of the JWT in milliseconds.
     */
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private JwtUserDetails myUserDetails;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Initializes the JwtProvider by encoding the secret key in Base64.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Creates a JWT token for a given user.
     *
     * @param user The user for whom the JWT is created.
     * @return A JWT token string.
     */
    public String createToken(XUser user) {

        Claims claims = Jwts.claims().setSubject(user.getUserName());
        claims.put("auth", user.getRoles().stream().filter(Objects::nonNull).collect(Collectors.toList()));
        claims.put("userId", personRepository.findByEmail(user.getUserName()).getId());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    /**
     * Retrieves the authentication information based on the JWT token.
     *
     * @param token The JWT token.
     * @return An Authentication object containing the user details.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token.
     * @return The username contained within the token.
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Resolves the JWT token from the HTTP request.
     *
     * @param req The HttpServletRequest containing the JWT token in its Authorization header.
     * @return The JWT token or null if not found.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Validates a JWT token.
     *
     * @param token The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     * @throws MyJwtException if the token is expired or invalid.
     */
    public boolean validateToken(String token) {
        System.out.println("Before validate token " + token);
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (MyJwtException | IllegalArgumentException e) {
            throw new MyJwtException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
