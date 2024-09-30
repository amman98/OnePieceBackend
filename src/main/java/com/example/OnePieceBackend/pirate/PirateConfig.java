package com.example.OnePieceBackend.pirate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PirateConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            PirateRepository repository) {
        return args -> {
            Pirate luffy = new Pirate(
				"Luffy",
				"Straw Hat",
				100,
                "Captain",
                19,
                "Gum-Gum",
                1L
			);

            Pirate zoro = new Pirate(
				"Zoro",
				"Pirate Hunter",
				50,
                "Fighter",
                21,
                null,
                1L
			);

            repository.saveAll(
                List.of(luffy, zoro)
            );
        };
    }
}
