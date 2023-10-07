package myboot.app1.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Un contrôleur pour gérer les films.
 */
@Controller()
public class HelloController {

	protected final Log logger = LogFactory.getLog(getClass());

	/*
	 * Récupérer un message particulier dans le fichier de configuration pour ne pas
	 * utiliser de constantes dans le code.
	 */
	@Value("${application.message:Hello World}")
	private String message;

	/**
	 * Une simple page de test (GET)
	 */
	@RequestMapping(value = "/hello")
	private ModelAndView hello() {
		return new ModelAndView("hello", "message", message);
	}

}
