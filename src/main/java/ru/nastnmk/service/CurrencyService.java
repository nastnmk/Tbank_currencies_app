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
        return currencyRepository.findAll();
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
        Currency currentCurrency = currencyRepository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found"));
        currentCurrency.setName(currency.getName());
        currentCurrency.setBaseCurrency(currency.getBaseCurrency());
        currentCurrency.setDescription(currency.getDescription());
        return currencyRepository.save(currentCurrency);
    }

    @Transactional
    public void deleteCurrency(UUID id){
        currencyRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll(){
        currencyRepository.deleteAll();
    }
}
