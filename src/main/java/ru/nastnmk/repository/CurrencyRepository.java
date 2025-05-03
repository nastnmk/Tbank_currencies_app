package ru.nastnmk.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.nastnmk.entity.Currency;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, UUID>{


}
