package ru.nastnmk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nastnmk.config.CurrencyConfig;
import ru.nastnmk.dto.CurrencyRateDTO;
import ru.nastnmk.dto.ValuteInfo;
import ru.nastnmk.entity.Currency;
import ru.nastnmk.repository.CurrencyRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(CurrencyConfig.class)
public class CurrencyCheckerService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CurrencyConfig currencyConfig;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public void checkCurrencyRates() {
        List<Currency> savedCurrencies = currencyRepository.findAllCurrencies();

        try {
            String jsonResponse = restTemplate.getForObject(currencyConfig.getCbApiUrl(), String.class);

            CurrencyRateDTO response = convert(jsonResponse);

            Map<String, ValuteInfo> valuteMap = response.getValute();

            for (Currency currency : savedCurrencies) {
                String charCode = currency.getBaseCurrency();

                if (!valuteMap.containsKey(charCode)) {
                    continue;
                }

                ValuteInfo apiValute = valuteMap.get(charCode);
                double value = apiValute.getValue();
                double previous = apiValute.getPrevious();

                double changePercent = ((value - previous) / previous) * 100;

                try {
                    String changeRange = currency.getPriceChangeRange()
                            .replace("%", "")
                            .replace(",", ".")
                            .trim();

                    double configuredChange = Double.parseDouble(changeRange);

                    if (configuredChange < 0 && changePercent <= configuredChange) {
                        System.out.println(currency.getDescription());
                    } else if (configuredChange > 0 && changePercent >= configuredChange) {
                        System.out.println(currency.getDescription());
                    }

                } catch (NumberFormatException e) {
                    System.out.printf(
                            "Некорректный формат priceChangeRange у валюты %s: %s%n",
                            currency.getName(),
                            currency.getPriceChangeRange()
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при получении или разборе данных с API: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private CurrencyRateDTO convert(String jsonResponse) {
        CurrencyRateDTO response = new CurrencyRateDTO();
        response.setValute(new HashMap<>());
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode valuteNode = root.get("Valute");

            if (valuteNode != null) {
                Iterator<Map.Entry<String, JsonNode>> fields = valuteNode.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    JsonNode node = entry.getValue();

                    ValuteInfo dto = new ValuteInfo();
                    dto.setCharCode(node.get("CharCode").asText());
                    dto.setName(node.get("Name").asText());
                    dto.setValue(node.get("Value").asDouble());
                    dto.setPrevious(node.get("Previous").asDouble());

                    response.getValute().put(dto.getCharCode(), dto);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CBR response", e);
        }
        return response;
    }
}
