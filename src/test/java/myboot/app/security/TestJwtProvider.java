package myboot.app.security;

import myboot.app.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "security.jwt.token.secret-key=thisIsASuperSecretKeyForJwtTestsYesItIsLong!",
        "security.jwt.token.expire-length=3600000"
})
public class TestJwtProvider {

    @Autowired
    private JwtProvider jwtProvider;

    private Person testPerson;

    @BeforeEach
    public void setUp() {
        testPerson = new Person();
        testPerson.setEmail("test@email.com");
        testPerson.setPassword("myPassword");
        testPerson.setFirstName("PersonJWT");
        testPerson.setLastName("PersonLastJWT");
    }

    @Test
    public void whenGenerateJwt_thenSuccess() {
        String token = jwtProvider.createToken(testPerson);
        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
    }

    @Test
    public void whenGetUsernameFromJwt_thenCorrect() {
        String token = jwtProvider.createToken(testPerson);
        String username = jwtProvider.getUsername(token);
        assertEquals(testPerson.getEmail(), username);
    }

    @Test
    public void whenValidateJwtWithWrongKey_thenFail() {
        String token = jwtProvider.createToken(testPerson);
        JwtProvider wrongKeyProvider = new JwtProvider();
        wrongKeyProvider.setSecretKey("thisIsTotalyWrongIJustAmCreatingATooLongKeyForThisTest");
        wrongKeyProvider.init();
        assertFalse(wrongKeyProvider.validateToken(token));
    }
}
