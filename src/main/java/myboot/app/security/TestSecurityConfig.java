package myboot.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration class for setting up security in test environments.
 * <p>
 * This configuration is activated when the 'test' profile is active.
 * It disables CSRF protection and permits all requests without any
 * authentication or authorization checks. This setup is typically used
 * in test scenarios where security constraints need to be relaxed.
 * </p>
 *
 * @see WebSecurityConfigurerAdapter
 */
@Configuration
@EnableWebSecurity
@Profile("test")
@Order(1)
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures the {@link HttpSecurity} to disable CSRF protection and
     * permit all incoming requests regardless of their authentication state.
     * <p>
     * This method overrides the default configuration to ensure that no
     * security constraints are applied, thereby allowing all requests to
     * proceed without any form of authentication or authorization during
     * testing.
     * </p>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().permitAll(); // We authorize all of requests for tests, no JWT verification is asked for
    }
}
