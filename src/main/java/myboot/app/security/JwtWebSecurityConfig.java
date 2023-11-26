package myboot.app.security;

import myboot.app.dao.XUserRepository;
import myboot.app.model.XUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Configures Spring Security for the application.
 * This configuration class is responsible for setting up security-related aspects,
 * such as CSRF protection, session management, route security, and CORS settings.
 * It also initializes some default users for testing purposes.
 */
@Configuration
@EnableWebSecurity
public class JwtWebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private XUserRepository userRepo;

    @Autowired
    private JwtProvider jwtTokenProvider;

    /**
     * Initializes the security configuration.
     * This method is called after the bean's properties have been set.
     * It creates default users and logs the initialization of Spring Security with JWT.
     */
    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        var aa = new XUser("aaa", encoder.encode("aaa"), Set.of("ADMIN", "USER"));
        var bb = new XUser("bbb", encoder.encode("bbb"), Set.of("USER"));
        userRepo.save(aa);
        userRepo.save(bb);
        logger.debug("--- INIT SPRING SECURITY JWT");
    }

    /**
     * Configures the {@link HttpSecurity} to set up security constraints for the application.
     * This method defines which routes are secured and which are public.
     * It also configures CSRF protection and session management strategy.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs during configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Pas de vérification CSRF (cross site request forgery)
        http.csrf().disable();

        // Spring security de doit gérer les sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Déclaration des end-points
        http.authorizeRequests()
                // Routes publiques
                .antMatchers("/secu-users/login").permitAll()
                .antMatchers("/secu-users/signup").permitAll()

                // Routes protégées nécessitant une authentification
                .antMatchers(HttpMethod.GET, "/secu-users", "/secu-users/me", "/secu-users/refresh", "/secu-users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/secu-users/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/persons").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/persons/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/persons/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/activities").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/activities/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/activities/**").authenticated()

                // Autoriser le reste...
                .anyRequest().permitAll();

        // Gestion d'accès refusé
        http.exceptionHandling().accessDeniedPage("/secu-users/login");

        // Mise en place du filtre JWT
        http.apply(new JwtFilterConfigurer(jwtTokenProvider));
    }

    /**
     * Creates a password encoder bean that will be used to encode and verify passwords.
     * The encoder uses BCrypt hashing algorithm with a strength of 12.
     *
     * @return a {@link PasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Exposes the {@link AuthenticationManager} as a Spring bean.
     * This is necessary to authenticate users in the authentication process.
     *
     * @return an {@link AuthenticationManager} bean
     * @throws Exception if an error occurs during bean creation
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings.
     * This setup allows for cross-origin requests from the specified origins and methods.
     *
     * @return a {@link WebMvcConfigurer} with CORS configuration
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
            }
        };
    }

}
