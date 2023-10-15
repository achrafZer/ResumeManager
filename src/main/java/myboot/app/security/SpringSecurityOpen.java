package myboot.app.security;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@Profile("open")
public class SpringSecurityOpen extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http//
				.csrf().disable()//
				.authorizeRequests()//
				.anyRequest().permitAll();
	}

}
