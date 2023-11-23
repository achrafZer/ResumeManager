package myboot.app.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String jwt;
    private Long userId;

    public LoginResponse(String jwt, Long userId) {
        this.jwt = jwt;
        this.userId = userId;
    }
}
