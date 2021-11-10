package co.atmax.cfg;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import co.atmax.dao.BookDao;
import co.atmax.dao.impl.BookDaoImpl;

@ComponentScan("co.atmax.dao.impl")
//@EnableWebMvc
@Configurable
public class PersistenceConfiguration {
	@Bean
	public BookDao getDao() {
		return new BookDaoImpl();
	}

}
