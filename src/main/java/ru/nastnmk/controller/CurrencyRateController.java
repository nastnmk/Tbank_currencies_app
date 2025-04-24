package ru.nastnmk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nastnmk.job.CurrencyRateJob;

@RestController
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateJob currencyRateJob;

    @GetMapping("/check-currency")
    public String checkCurrency() {
        currencyRateJob.checkRatesHourly();
        return "Проверка курса валют выполнена вручную!";
    }
}