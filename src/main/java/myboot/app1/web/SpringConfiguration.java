package myboot.app1.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import myboot.Starter;

@Configuration
public class SpringConfiguration extends SpringBootServletInitializer implements WebMvcConfigurer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Starter.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("--- addResourceHandlers");
		registry.addResourceHandler("/webjars/**")//
				.addResourceLocations("/webjars/");
	}

	@Bean("messageSource")
	public MessageSource messageSource() {
		var r = new ReloadableResourceBundleMessageSource();
		r.setBasenames("classpath:messages");
		return r;
	}

	@Override
	public LocalValidatorFactoryBean getValidator() {
		var factory = new LocalValidatorFactoryBean();
		factory.setValidationMessageSource(messageSource());
		return factory;
	}

	@Override
	// Pour activer les variables matrix
	public void configurePathMatch(PathMatchConfigurer configurer) {
		var urlPathHelper = new UrlPathHelper();
		// Nous gardons le contenu après le point virgule
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

}
