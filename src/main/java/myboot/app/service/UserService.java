package myboot.app.service;

import myboot.app.dao.PersonRepository;
import myboot.app.dao.XUserRepository;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.Person;
import myboot.app.model.XUser;
import myboot.app.security.JwtProvider;
import myboot.app.security.MyJwtException;
import myboot.app.web.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Service class for handling user-related operations.
 * This class provides methods for user authentication, registration, deletion, and information retrieval.
 */
@Service
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

    /**
     * Authenticates a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A LoginResponse containing the JWT and user's ID if authentication is successful.
     * @throws MyJwtException If authentication fails or the user is not found.
     */
    public LoginResponse login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = userRepository.findById(username).orElseThrow(() -> new MyJwtException("User not found", HttpStatus.NOT_FOUND));

            String jwt = jwtTokenProvider.createToken(user);

            return new LoginResponse(jwt, personRepository.findByEmail(username).getId());

        } catch (AuthenticationException e) {
            throw new MyJwtException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Registers a new user based on the provided registration details.
     *
     * @param registrationDTO The DTO containing the registration details.
     * @return A JWT token for the newly created user.
     * @throws MyJwtException If the username (email) is already in use.
     */
    public String signup(RegistrationDTO registrationDTO) {
        String userName = registrationDTO.getEmail();
        if (userRepository.findById(userName).isPresent()) {
            throw new MyJwtException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        //When a new person is registered, we create at the same time an instance of Person and another one of XUser

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

    /**
     * Deletes a user with the specified username.
     *
     * @param username The username of the user to be deleted.
     */
    public void delete(String username) {
        userRepository.deleteById(username);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The found XUser object.
     * @throws MyJwtException If the user does not exist.
     */
    public XUser search(String username) {
        return userRepository.findById(username).orElseThrow(() -> new MyJwtException("The user doesn't exist", HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves the current logged-in user.
     *
     * @param req The HttpServletRequest containing the JWT token.
     * @return The logged-in XUser object.
     */
    public XUser whoami(HttpServletRequest req) {
        return search(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    /**
     * Refreshes the JWT token for a user.
     *
     * @param username The username of the user.
     * @return A new JWT token.
     */
    public String refresh(String username) {
        return jwtTokenProvider.createToken(userRepository.findById(username).get());
    }

    /**
     * Saves or updates a user in the database.
     *
     * @param user The XUser object to be saved or updated.
     */
    public void saveUser(XUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all XUser objects.
     */
    public List<XUser> getAllUsers() {
        return userRepository.findAll();
    }
}
