package ru.nastnmk.job;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nastnmk.service.CurrencyCheckerService;

@Component
@RequiredArgsConstructor
public class CurrencyRateJob {

    private final CurrencyCheckerService currencyCheckerService;

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")
    public void checkRatesHourly() {
        System.out.println("Запуск проверки валют...");
        currencyCheckerService.checkCurrencyRates();
    }

}