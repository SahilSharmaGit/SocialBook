package co.atmax.cfg;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.atmax.dao.BookDao;
import co.atmax.dao.impl.BookDaoImpl;
import co.atmax.service.BookService;
import co.atmax.service.impl.BookServiceImpl;
import co.atmax.validation.service.BookUserValidator;

@ComponentScan("co.atmax.dao.impl")
//@EnableWebMvc
@Configurable
public class PersistenceConfiguration {
	@Bean
	public BookDao getDao() {
		return new BookDaoImpl();
	}

}
