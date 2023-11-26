package myboot.app.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configures the security filter chain to incorporate the JWT (JSON Web Token) filter.
 * This class is responsible for integrating the JwtFilter into the Spring Security filter chain.
 *
 * The JwtFilter is added before the UsernamePasswordAuthenticationFilter to intercept and process
 * the JWT token from incoming HTTP requests.
 */
public class JwtFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    final private JwtProvider jwtTokenProvider;

    /**
     * Constructor for JwtFilterConfigurer.
     *
     * @param jwtTokenProvider the JWT token provider to be used by the JwtFilter
     */
    public JwtFilterConfigurer(JwtProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        System.out.println("Init JwtTokenFilterConfigurer");
    }

    /**
     * Configures the HttpSecurity to add the JwtFilter.
     * This method adds the JwtFilter into the HttpSecurity's filter chain.
     *
     * @param http the HttpSecurity to be configured
     * @throws Exception if an error occurs during configuration
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtFilter customFilter = new JwtFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
