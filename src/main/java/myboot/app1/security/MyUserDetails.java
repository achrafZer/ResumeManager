package myboot.app1.security;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myboot.app1.dao.XUserRepository;

@Service
@Profile("simple")
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private XUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final var appUser = userRepository.findById(username);

		if (appUser.isEmpty()) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		final var user = appUser.get();
		var authorites = new LinkedList<GrantedAuthority>();
		user.getRoles().forEach((role) -> {
			authorites.add(new SimpleGrantedAuthority(role));
		});
		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(user.getPassword())//
				.authorities(authorites)//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

}
