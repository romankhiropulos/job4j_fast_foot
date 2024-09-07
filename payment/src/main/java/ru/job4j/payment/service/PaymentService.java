package ru.job4j.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.job4j.payment.entity.Account;
import ru.job4j.payment.entity.Country;
import ru.job4j.payment.repository.AccountMongoRepository;
import ru.job4j.payment.repository.CountryMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final AccountMongoRepository accountRepository;
    private final CountryMongoRepository countryMongoRepository;

    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    public void processAccounts() {
        if (accountRepository.count() == 0) {
            List<Country> countries = List.of(new Country(new ObjectId().toString(), "Russia"),
                                              new Country(new ObjectId().toString(), "Germany"),
                                              new Country(new ObjectId().toString(), "USA"));
            List<Country> countryListFromDb = countryMongoRepository.saveAll(countries);
            Account accountOne = Account.builder()
                    .money(1000d)
                    .region("Belgrade")
                    .firstName("Vlad")
                    .lastName("Ivanov")
                    .country(countryListFromDb.get(0))
                    .build();
            Account accountTwo = Account.builder()
                    .money(2000d)
                    .region("London")
                    .firstName("Mary")
                    .lastName("Watson")
                    .country(countryListFromDb.get(1))
                    .build();
            Account accountThree = Account.builder()
                    .money(3000d)
                    .region("Los-Angeles")
                    .firstName("Steve")
                    .lastName("Black")
                    .country(countryListFromDb.get(2))
                    .build();
            accountRepository.saveAll(List.of(accountOne, accountTwo, accountThree));
        }
    }
}
