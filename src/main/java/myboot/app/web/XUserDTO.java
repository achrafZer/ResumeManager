package myboot.app.web;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUserDTO {

	private String userName;
	private String password;
	private List<String> roles;

}
