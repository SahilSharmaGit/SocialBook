package co.atmax.cfg;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configurable
@ComponentScan("co.atmax")
@Import({PersistenceConfiguration.class, ServiceConfiguration.class, SecondaryConfiguration.class})
public class Configuration {

}
