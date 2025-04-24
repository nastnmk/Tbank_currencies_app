package ru.nastnmk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.nastnmk.service.CurrencyCheckerService;
import ru.nastnmk.service.CurrencyService;

import ru.nastnmk.entity.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        currencyService.addCurrency(currency);
        return ResponseEntity.status(201).body(currency);
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable String id){
        return ResponseEntity.ok(currencyService.getCurrencyById(UUID.fromString(id)));
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable String id, @RequestBody Currency currency){
        return ResponseEntity.ok(currencyService.updateCurrency(UUID.fromString(id),currency));
    }

    @DeleteMapping("/currencies/{id}")
    public ResponseEntity<Currency> deleteCurrency(@PathVariable String id){
        currencyService.deleteCurrency(UUID.fromString(id));
        return null;
    }

    @DeleteMapping("/currencies")
    public ResponseEntity<Currency> deleteAll(){
        currencyService.deleteAll();
        return null;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                error.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(error);
    }

}
