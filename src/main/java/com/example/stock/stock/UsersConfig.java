package com.example.stock.stock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import java.time.LocalDate;
import java.util.*;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository){
        return args -> {
            Users test1 = new Users(
                    "test1"
            );

            Users test2 = new Users(
                    "test2"
            );

            repository.saveAll(
                    List.of(test1, test2)
            );
        };
    }
}