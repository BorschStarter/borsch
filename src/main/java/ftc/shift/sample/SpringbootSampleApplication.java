package ftc.shift.sample;

import ftc.shift.sample.repositories.classes.InMemoryFoodRepository;
import ftc.shift.sample.repositories.classes.InMemoryUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootSampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootSampleApplication.class, args);
	}
}