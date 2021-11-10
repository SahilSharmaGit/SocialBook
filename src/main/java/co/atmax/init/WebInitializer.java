package co.atmax.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import co.atmax.cfg.Configuration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {Configuration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/rest/*"};
	}
	
}