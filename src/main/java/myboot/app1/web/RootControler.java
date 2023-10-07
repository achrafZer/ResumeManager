package myboot.app1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootControler {

	/*
	 * Point d'entrée principal de l'application.
	 */
	@RequestMapping("/")
	public String index() {
		return "redirect:/movies";
	}

}