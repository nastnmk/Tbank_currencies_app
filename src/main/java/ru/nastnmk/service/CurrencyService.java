package ru.nastnmk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nastnmk.entity.Currency;
import ru.nastnmk.repository.CurrencyRepository;

import javax.crypto.Cipher;
import java.util.List;
import java.util.Spliterator;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Transactional(readOnly = true)
    public List<Currency> getCurrencies(){
        return currencyRepository.findAllCurrencies();
    }

    @Transactional
    public void addCurrency(Currency currency){
        currencyRepository.save(currency);
    }

    @Transactional(readOnly = true)
    public Currency getCurrencyById(UUID id) {
        return currencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Currency not found: " + id));
    }

    @Transactional
    public Currency updateCurrency(UUID id, Currency currency){
        Currency currency_now = currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found"));
        currency_now.setName(currency.getName());
        currency_now.setBaseCurrency(currency.getBaseCurrency());
        currency_now.setDescription(currency.getDescription());
        return currencyRepository.save(currency_now);
    }

    @Transactional
    public void deleteCurrency(UUID id){
        currencyRepository.deleteById(id);
    }
}
