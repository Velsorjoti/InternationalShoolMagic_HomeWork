package ru.magic.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class InternationalSchoolMagicHomeWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternationalSchoolMagicHomeWorkApplication.class, args);
    }

}
