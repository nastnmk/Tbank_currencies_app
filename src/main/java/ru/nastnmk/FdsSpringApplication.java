package ru.nastnmk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.nastnmk.config.CurrencyConfig;
import ru.nastnmk.controller.CurrencyController;
import ru.nastnmk.entity.Currency;
import ru.nastnmk.repository.CurrencyRepository;

import java.util.UUID;


@SpringBootApplication(scanBasePackages = "ru.nastnmk")
@EnableConfigurationProperties(CurrencyConfig.class)
public class FdsSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(FdsSpringApplication.class, args).getBeanFactory().getBean(CurrencyController.class).addCurrency(new Currency(null, "name", "base", "price", "desc"));
//        SpringApplication.run(FdsSpringApplication.class, args).getBean(CurrencyRepository.class).deleteAll();
    }
}
