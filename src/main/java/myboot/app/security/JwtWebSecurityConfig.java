package myboot.app.security;

import myboot.app.dao.XUserRepository;
import myboot.app.model.XUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.HttpMethod;


import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Configuration de Spring Security.
 */
@Configuration
@EnableWebSecurity
@Profile("usejwt")
public class JwtWebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private XUserRepository userRepo;

    @Autowired
    private JwtProvider jwtTokenProvider;

    @PostConstruct
    public void init() {
        var encoder = passwordEncoder();
        var aa = new XUser("aaa", encoder.encode("aaa"), Set.of("ADMIN", "USER"));
        var bb = new XUser("bbb", encoder.encode("bbb"), Set.of("USER"));
        userRepo.save(aa);
        userRepo.save(bb);
        logger.debug("--- INIT SPRING SECURITY JWT");
    }

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


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
