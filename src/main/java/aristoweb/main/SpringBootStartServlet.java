package aristoweb.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import aristoweb.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
/*@EnableAutoConfiguration
@Configuration
@ComponentScan as @SpringBootApplication means all 3*/
@SpringBootApplication(scanBasePackages={"aristoweb"})

public class SpringBootStartServlet extends SpringBootServletInitializer 
{
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringBootStartServlet.class);
  }

  public static void main(String[] args) {
      SpringApplication.run(SpringBootStartServlet.class, args);
  }
}