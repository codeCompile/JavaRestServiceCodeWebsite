package aristoweb.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import aristoweb.repository.PostRepository;
import aristoweb.springboot.configuration.JpaConfiguration;

/*@SpringBootApplication
public class SpringBootInitializerMainMethod implements CommandLineRunner 
{
	@Autowired
    PostRepository orderRepository;

    public static void main(String[] args) {
		SpringApplication.run(SpringBootInitializerMainMethod.class, args);
	}

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}*/


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"aristoweb"})
public class aristowebSpringBootInitializerMain {
 
    public static void main(String[] args) {
        SpringApplication.run(aristowebSpringBootInitializerMain.class, args);
    }
}

