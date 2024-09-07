package ru.job4j.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.payment.entity.Account;

import java.util.List;

@Repository("accountRepository")
public interface AccountMongoRepository extends MongoRepository<Account, String> {

    List<Account> findAll();
}
