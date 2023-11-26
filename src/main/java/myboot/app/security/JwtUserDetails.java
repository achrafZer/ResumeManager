package myboot.app.security;

import myboot.app.dao.XUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Service providing user details for JWT authentication.
 * This class is responsible for loading user-specific data for Spring Security.
 * It uses data from the {@link XUserRepository} to build {@link UserDetails} objects.
 */
@Service
public class JwtUserDetails implements UserDetailsService {

    @Autowired
    private XUserRepository userRepository;

    /**
     * Load user by username.
     * This method is used by Spring Security to load details about a user
     * during authentication processes.
     *
     * @param username The username of the user to be loaded.
     * @return UserDetails containing user's information, roles, and other security-related data.
     * @throws UsernameNotFoundException If the user with the given username cannot be found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findById(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        });

        var authorities = user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.getPassword())//
                .authorities(authorities)//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
