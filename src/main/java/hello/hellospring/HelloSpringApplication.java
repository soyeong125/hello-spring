package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		//SpringApplication.run(HelloSpringApplication.class, args);
		SpringApplication application = new SpringApplication(HelloSpringApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
