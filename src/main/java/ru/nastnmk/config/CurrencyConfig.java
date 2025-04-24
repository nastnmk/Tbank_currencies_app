package ru.nastnmk.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "currency-tracker")
public class CurrencyConfig {
    private String cbApiUrl;
}

