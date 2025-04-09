package ru.nastnmk.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "currencies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "base_currency", nullable = false)
    private String baseCurrency = "RUB";

    @Column(name = "price_change_range")
    private String priceChangeRange;

    private String description;
}
