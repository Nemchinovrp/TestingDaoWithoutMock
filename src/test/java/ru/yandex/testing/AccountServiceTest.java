package ru.yandex.testing;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.testing.jpa.entity.Account;
import ru.yandex.testing.service.AccountService;

/**
 * @author nemchinovrp
 */
class AccountServiceTest extends FunctionalTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DatabaseSetup("classpath:account.xml")
    @DatabaseTearDown("classpath:empty-account.xml")
    void getAccount() {
        Account account = accountService.getAccount(100)
                .orElseThrow(() -> new IllegalArgumentException());
        Assertions.assertEquals(
                new Account(100, 500.0),
                account
        );
    }

    @Test
    @ExpectedDatabase("classpath:expectedAccount.xml")
    void createAccount() {
        Account account = accountService.createAccount(5000);
        Assertions.assertEquals(
                new Account(101, 5000.0),
                account
        );
    }
}
