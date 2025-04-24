package ru.nastnmk.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValuteInfo {
    private String charCode;
    private String name;
    private double value;
    private double previous;

}
