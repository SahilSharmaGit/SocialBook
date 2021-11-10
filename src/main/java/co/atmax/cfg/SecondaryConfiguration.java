package co.atmax.cfg;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.atmax.validation.service.BookUserValidator;

@ComponentScan("co.atmax.validation.service")
//@EnableWebMvc
@Configurable
public class SecondaryConfiguration {

	@Bean
	public BookUserValidator getValidationService() {
		return new BookUserValidator();
	}

}
