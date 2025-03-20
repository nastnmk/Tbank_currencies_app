package ru.nastnmk.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    private String id;
    private String name;
    private String baseCurrency = "RUB";
    private String priceChangeRange;
    private String description;

    public void setId(String id) {
        this.id = id;
    }
}
