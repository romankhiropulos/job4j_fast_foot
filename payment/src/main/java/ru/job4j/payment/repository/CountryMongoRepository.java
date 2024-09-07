package ru.job4j.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.payment.entity.Country;

import java.util.List;

@Repository("countryRepository")
public interface CountryMongoRepository extends MongoRepository<Country, String> {

    List<Country> findAll();
}
