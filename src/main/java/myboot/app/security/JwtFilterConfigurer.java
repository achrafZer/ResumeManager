package myboot.app5.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Classe de mise en place du filtre JWT
 */
public class JwtFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	final private JwtProvider jwtTokenProvider;

	public JwtFilterConfigurer(JwtProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
		System.out.println("Init JwtTokenFilterConfigurer");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtFilter customFilter = new JwtFilter(jwtTokenProvider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
