package com.tstProject.demo.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args ->{
            Student gabriel = new Student(
                1L,
                "Gabriel",
                LocalDate.of(2024, Month.JANUARY, 15),
                "GabrielRed@gmail.com"
            );
            
            Student alexis = new Student(
                "Alexis",
                LocalDate.of(2000, Month.FEBRUARY, 18),
                "AlexisRed@gmail.com"
            );

            studentRepository.saveAll(
                List.of(gabriel,
                alexis)
            );
        };
    }
}
