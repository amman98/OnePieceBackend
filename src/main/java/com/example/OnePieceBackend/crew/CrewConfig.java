package com.example.OnePieceBackend.crew;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrewConfig {
    
    @Bean
    CommandLineRunner crewCommandLineRunner(
            CrewRepository repository) {
        return args -> {
            Crew strawHats = new Crew(
                "Straw Hat Pirates"
            );

            Crew redHair = new Crew(
                "Red Hair Pirates"
            );

            repository.saveAll(
                List.of(strawHats, redHair)
            );
        };
    }
}
