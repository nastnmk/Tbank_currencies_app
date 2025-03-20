package ru.nastnmk.service;

import org.springframework.stereotype.Service;
import ru.nastnmk.model.Currency;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService {
    private List<Currency> list_of_currencies = new ArrayList<>();

    public List<Currency> getCurrencies(){
        return list_of_currencies;
    }

    public Currency addCurrency(Currency currency){
        currency.setId((UUID.randomUUID().toString()));
        list_of_currencies.add(currency);
        return currency;
    }

    public Currency getCurrencyById(String id){
        return null;
    }

    public Currency updateCurrency(String id){
        return null;
    }

    public void deleteCurrency(String id){
    }
}
