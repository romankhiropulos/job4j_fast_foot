package ru.job4j.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.dto.AccountDto;
import ru.job4j.payment.entity.Account;
import ru.job4j.payment.service.PaymentService;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private static final String ACCOUNT_NOT_FOUND_BY_ID = "Account is not found for id: %s. Please, check request.";
    private static final String HEADER_NAME = "HeaderPayment";
    private static final String HEADER_VALUES = "job4j_payment";
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @GetMapping("account/all")
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        List<Account> allAccount = paymentService.findAllAccount();
        return ResponseEntity.status(allAccount.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                .header(HEADER_NAME, HEADER_VALUES)
                .body(allAccount.stream()
                        .map(a -> objectMapper.convertValue(a, AccountDto.class))
                        .toList());
    }

    @GetMapping("account/{id}")
    public ResponseEntity<AccountDto> findById(@PathVariable String id) {
        var account = this.paymentService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, String.format(ACCOUNT_NOT_FOUND_BY_ID, id)
        ));
        return new ResponseEntity<>(objectMapper.convertValue(account, AccountDto.class), HttpStatus.OK);
    }

    @PostMapping("account/save")
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto) {
        validatePerson(objectMapper.convertValue(accountDto, Account.class));
        Account account = paymentService.save(objectMapper.convertValue(accountDto, Account.class));
        return new ResponseEntity<>(
                objectMapper.convertValue(account, AccountDto.class),
                HttpStatus.CREATED
        );
    }

    /**
     * Наполняет базу данных тестовыми аккаунтами, если в базе аккаунтов нет
     *
     * @return ResponseEntity with status Ok
     */
    @PostMapping("account/process")
    public ResponseEntity<?> processCountries() {
        paymentService.processAccounts();
        return ResponseEntity.ok().build();
    }

    private void validatePerson(Account account) throws NullPointerException {
        String errMsg = "FirstName, lastName and money must not be empty";
        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        Double money = account.getMoney();
        Objects.requireNonNull(firstName, errMsg);
        Objects.requireNonNull(lastName, errMsg);
        Objects.requireNonNull(money, errMsg);
        firstName = firstName.strip();
        lastName = lastName.strip();
        if (Objects.equals(firstName, "") || Objects.equals(lastName, "")) {
            throw new NullPointerException(errMsg);
        }
    }
}
