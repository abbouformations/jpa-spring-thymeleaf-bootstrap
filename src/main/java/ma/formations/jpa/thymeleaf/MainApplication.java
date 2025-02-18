package ma.formations.jpa.thymeleaf;

import ma.formations.jpa.thymeleaf.dtos.PersonDto;
import ma.formations.jpa.thymeleaf.service.IService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(IService service) {
		return (args -> {
			service.save(PersonDto.builder().firstname("Ahmed").lastname("Alami").age(44).married(true).build());
			service.save(PersonDto.builder().firstname("Mohamed").lastname("Samir").age(55).married(true).build());
			service.save(PersonDto.builder().firstname("Hanan").lastname("Issami").age(76).married(true).build());
			service.save(PersonDto.builder().firstname("Imad").lastname("Hassaoui").age(21).married(false).build());
			service.save(PersonDto.builder().firstname("Rabie").lastname("Sefiani").age(13).married(false).build());
			service.save(PersonDto.builder().firstname("Karim").lastname("Ahmadi").age(50).married(false).build());

		});
	}

}
