package com.cinema.recommendations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecommendationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecommendationsApplication.class, args);
	}

	// Ma ei ole kindel, kas seda ei peaks üle viima kausta util ja tegema factory või jätma alles.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
