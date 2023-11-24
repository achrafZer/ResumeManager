package myboot.app.web;

import javax.servlet.http.HttpServletRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import myboot.app.dto.LoginDTO;
import myboot.app.dto.RegistrationDTO;
import myboot.app.model.XUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myboot.app.service.UserService;

import java.util.List;

/**
 * L'API d'authentification
 */
@RestController
@RequestMapping("/secu-users")
@Profile("usejwt")
public class UserController {

	@Autowired
	private UserService userService;

	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Authentification et récupération d'un JWT
	 */
	@PostMapping("/login")
	@Operation(summary = "Authentification et récupération d'un JWT")
	@ApiResponse(responseCode = "200", description = "Connexion réussie et JWT récupéré")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
		LoginResponse loginResponse = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
		return ResponseEntity.ok(loginResponse);
	}

	@GetMapping()
	@Operation(summary = "Récupérer tous les utilisateurs")
	@ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès")
	public ResponseEntity<List<XUser>> getAllUsers() {
		List<XUser> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * Ajouter un utilisateur
	 */
	@PostMapping("/signup")
	@Operation(summary = "Ajouter un utilisateur")
	@ApiResponse(responseCode = "201", description = "Utilisateur enregistré avec succès")
	public ResponseEntity<String> signup(@RequestBody RegistrationDTO registrationDTO) {
		userService.signup(registrationDTO);
		return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	}

	/**
	 * Supprimer un utilisateur
	 */
	@DeleteMapping("/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Supprimer un utilisateur")
	@ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès")
	public String delete(@PathVariable String username) {
		userService.delete(username);
		return username;
	}

	/**
	 * Récupérer des informations sur un utilisateur
	 */
	@GetMapping("/{username}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Récupérer des informations sur un utilisateur")
	@ApiResponse(responseCode = "200", description = "Informations de l'utilisateur récupérées avec succès")
	public XUserDTO search(@PathVariable String username) {
		return modelMapper.map(userService.search(username), XUserDTO.class);
	}

	/**
	 * Récupérer des informations sur l'utilisateur courant
	 */
	@GetMapping(value = "/me")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@Operation(summary = "Récupérer des informations sur l'utilisateur courant")
	@ApiResponse(responseCode = "200", description = "Informations de l'utilisateur courant récupérées avec succès")
	public XUserDTO whoami(HttpServletRequest req) {
		return modelMapper.map(userService.whoami(req), XUserDTO.class);
	}

	/**
	 * Récupérer un nouveau JWT
	 */
	@GetMapping("/refresh")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@Operation(summary = "Récupérer un nouveau JWT")
	@ApiResponse(responseCode = "200", description = "Nouveau JWT récupéré avec succès")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}

}
