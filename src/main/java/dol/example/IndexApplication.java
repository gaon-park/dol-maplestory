package dol.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dol.example"})
public class IndexApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndexApplication.class, args);
    }
}
