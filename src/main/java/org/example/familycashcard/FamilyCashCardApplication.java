package org.example.familycashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/Family")
public class FamilyCashCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyCashCardApplication.class, args);
    }

    @GetMapping
    public String home() {
        return "Hello World!";
    }
}
