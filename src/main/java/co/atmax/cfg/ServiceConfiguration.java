package co.atmax.cfg;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import co.atmax.service.BookService;
import co.atmax.service.impl.BookServiceImpl;

@ComponentScan("co.atmax.service.impl")
//@EnableWebMvc
@Configurable
public class ServiceConfiguration {

	@Bean
	public BookService getService()
	{
		return new BookServiceImpl();
	}

}
