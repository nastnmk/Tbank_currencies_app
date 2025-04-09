package ru.nastnmk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nastnmk.controller.CurrencyController;
import ru.nastnmk.entity.Currency;

import java.util.UUID;


@SpringBootApplication
public class FdsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdsSpringApplication.class, args)
                .getBeanFactory().getBean(CurrencyController.class).addCurrency(new Currency(null, "name", "base", "price", "desc"));
    }
}
