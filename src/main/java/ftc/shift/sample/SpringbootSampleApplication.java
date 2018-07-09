package ftc.shift.sample;

import ftc.shift.sample.repositories.classes.InMemoryFoodRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootSampleApplication {

	public static void main(String[] args) {

		InMemoryFoodRepository foodRepository = new InMemoryFoodRepository();

		SpringApplication.run(SpringbootSampleApplication.class, args);
	}
}
