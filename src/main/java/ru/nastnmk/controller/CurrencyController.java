package ru.nastnmk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nastnmk.service.CurrencyService;

import ru.nastnmk.model.Currency;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies(){
        return ResponseEntity.ok(currencyService.getCurrencies());
    }

    @PostMapping("/currencies")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency){
        Currency newCurrency = currencyService.addCurrency(currency);
        return ResponseEntity.status(201).body(newCurrency);
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id){
        return ResponseEntity.ok(currencyService.getCurrencyById(id));
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id){
        return ResponseEntity.ok(currencyService.updateCurrency(id));
    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable String id){
        currencyService.deleteCurrency(id);
        return ResponseEntity.noContent().build();
    }
}
