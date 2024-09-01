package ru.job4j.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.payment.entity.Account;
import ru.job4j.payment.repository.AccountMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final AccountMongoRepository accountRepository;

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
            Account accountOne = Account.builder()
                    .money(1000d)
                    .region("Belgrade")
                    .firstName("Vlad")
                    .lastName("Ivanov")
                    .build();
            Account accountTwo = Account.builder()
                    .money(2000d)
                    .region("London")
                    .firstName("Mary")
                    .lastName("Watson")
                    .build();
            Account accountThree = Account.builder()
                    .money(3000d)
                    .region("Los-Angeles")
                    .firstName("Steve")
                    .lastName("Black")
                    .build();
            accountRepository.saveAll(List.of(accountOne, accountTwo, accountThree));
        }
    }
}
