package myboot.app5.security;

import java.util.Set;

import javax.annotation.PostConstruct;

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

import myboot.app.dao.XUserRepository;
import myboot.app.model.XUser;

/**
 * Configuration de Spring Security.
 */
@Configuration
@EnableWebSecurity
@Profile("usejwt")
public class JwtWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private XUserRepository userRepo;
	
	@Autowired
	private JwtProvider jwtTokenProvider;

	protected final Log logger = LogFactory.getLog(getClass());

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
		http.authorizeRequests()//
				.antMatchers("/secu-users/login").permitAll()//
				.antMatchers("/secu-users/signup").permitAll()//
				.antMatchers("/secu-users/**").authenticated()//
				// Autoriser le reste...
				.anyRequest().permitAll();

		// Pas vraiment nécessaire
		http.exceptionHandling().accessDeniedPage("/secu-users/login");

		// Mise en place du filtre JWT
		http.apply(new JwtFilterConfigurer(jwtTokenProvider));

		// Optional, if you want to test the API from a browser
		// http.httpBasic();
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

}
