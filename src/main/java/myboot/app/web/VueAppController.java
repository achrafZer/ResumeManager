package myboot.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class VueAppController {

    @RequestMapping(value = "/app/**")
    private ModelAndView app() {
        return new ModelAndView("app");
    }

    @RequestMapping(value = "/")
    private ModelAndView home() {
        return new ModelAndView(new RedirectView("/app/"));
    }
}
