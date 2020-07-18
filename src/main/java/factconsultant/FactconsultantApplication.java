package factconsultant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class FactconsultantApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FactconsultantApplication.class, args);
	}
	/*
	* This method is invoked when the app runs as a war file Add application level
	* instantiations in the below method or use AddInitializer proxy
	*/ 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(FactconsultantApplication.class);
	}
	
}
