package myboot.app.service;

import javax.servlet.http.HttpServletRequest;

import myboot.app.dao.PersonRepository;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.Person;
import myboot.app5.security.JwtProvider;
import myboot.app5.security.MyJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import myboot.app.dao.XUserRepository;
import myboot.app.model.XUser;

@Service
@Profile("usejwt")
public class UserService {

	@Autowired
	private XUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PersonRepository personRepository;

	public String login(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			var user = userRepository.findById(username).get();
			return jwtTokenProvider.createToken(user);
		} catch (AuthenticationException e) {
			throw new MyJwtException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(RegistrationDTO registrationDTO) {
		String userName = registrationDTO.getEmail();
		if (userRepository.findById(userName).isPresent()) {
			throw new MyJwtException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		//When a new person is registered, we create at the same time an insance of Person and another one of XUser

		Person person = new Person();
		person.setFirstName(registrationDTO.getFirstName());
		person.setLastName(registrationDTO.getLastName());
		person.setEmail(registrationDTO.getEmail());
		person.setWebsite(registrationDTO.getWebsite());
		person.setBirthDate(registrationDTO.getBirthDate());
		person.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		personRepository.save(person);

		XUser user = new XUser();
		user.setUserName(userName);
		user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		user.setRoles(registrationDTO.getRoles());
		userRepository.save(user);
		return jwtTokenProvider.createToken(user);
	}

	public void delete(String username) {
		userRepository.deleteById(username);
	}

	public XUser search(String username) {
		return userRepository.findById(username)
				.orElseThrow(() -> new MyJwtException("The user doesn't exist", HttpStatus.NOT_FOUND));
	}

	public XUser whoami(HttpServletRequest req) {
		return search(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public String refresh(String username) {
		return jwtTokenProvider.createToken(userRepository.findById(username).get());
	}

	public void saveUser(XUser user) {
		userRepository.save(user);
	}

}
