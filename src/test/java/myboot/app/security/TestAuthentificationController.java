package myboot.app.security;

import myboot.app.model.XUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestAuthentificationController {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/secu-users";
    }

    @Test
    public void testSignup() {
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        XUser user = new XUser("testUser", "testPassword", roles);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<XUser> requestEntity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl + "/signup",
                HttpMethod.POST,
                requestEntity,
                String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSignupAndLogin() {
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        XUser newUser = new XUser("testUserSignupLogin", "testPassword", roles);

        HttpHeaders signupHeaders = new HttpHeaders();
        signupHeaders.setContentType(MediaType.APPLICATION_JSON);
        signupHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<XUser> signupRequestEntity = new HttpEntity<>(newUser, signupHeaders);

        ResponseEntity<String> signupResponse = restTemplate.exchange(
                baseUrl + "/signup",
                HttpMethod.POST,
                signupRequestEntity,
                String.class);

        assertEquals(HttpStatus.OK, signupResponse.getStatusCode());


        MultiValueMap<String, String> loginFormData = new LinkedMultiValueMap<>();
        loginFormData.add("username", "testUserSignupLogin");
        loginFormData.add("password", "testPassword");

        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        loginHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, String>> loginRequestEntity = new HttpEntity<>(loginFormData, loginHeaders);

        ResponseEntity<String> loginResponse = restTemplate.exchange(
                baseUrl + "/login",
                HttpMethod.POST,
                loginRequestEntity,
                String.class);

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
    }



}
