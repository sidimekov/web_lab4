package ru.sidimekov.web_lab4_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.sidimekov.web_lab4_backend.repository")
@EntityScan("ru.sidimekov.web_lab4_backend.model")
//@SpringBootApplication(scanBasePackages = "ru.sidimekov.web_lab4_backend")
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}