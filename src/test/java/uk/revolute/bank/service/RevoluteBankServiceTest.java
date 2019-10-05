package uk.revolute.bank.service;

import javafx.scene.input.TransferMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.revolute.bank.dto.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.exception.ServiceException;

import java.time.LocalDate;
import java.time.LocalDateTime;

class RevoluteBankServiceTest {

    static long accountID_1 =0;
    static long accountID_2 =0;

    @BeforeAll
    public static void testCreateAccounts() throws Exception {
        uk.revolute.bank.dto.Account account = new uk.revolute.bank.dto.Account();
        account.setCurrentBalace(1000);
        account.setActive(true);
        account.setBranch("London");
        account.setAccountHolderName("Ganesha");
        account.setAccountHolderDOB(LocalDate.of(1991, 6, 30));
        account.setCreatedDate(LocalDateTime.now());
        account.setUpdatedDate(LocalDateTime.now());
        accountID_1 =  RevoluteBankService.getInstance().createAccounts(account);

        uk.revolute.bank.dto.Account account1 = new Account();
        account1.setCurrentBalace(2000);
        account1.setActive(true);
        account1.setBranch("Leeds");
        account.setAccountHolderName("Shiva");
        account.setAccountHolderDOB(LocalDate.of(1980, 6, 03));
        account1.setCreatedDate(LocalDateTime.now());
        account1.setUpdatedDate(LocalDateTime.now());
        accountID_2 =  RevoluteBankService.getInstance().createAccounts(account1);

        Assertions.assertTrue(accountID_1>0);
        Assertions.assertTrue(accountID_2>0);
    }

    @Test
    public void testMoneyTransfer() throws Exception {
        MonenyTransfer mt = new MonenyTransfer();
        mt.setAmount(100);
        mt.setFromAccountNumber(accountID_1);
        mt.setToAccountNumber(accountID_2);
        RevoluteBankService.getInstance().transferMoney(mt);

        uk.revolute.bank.domain.Account account = RevoluteBankService.getInstance().getAccountSummary(accountID_1);
        Assertions.assertTrue(account.getCurrentBalace()==900);

        uk.revolute.bank.domain.Account account1 = RevoluteBankService.getInstance().getAccountSummary(accountID_2);
        Assertions.assertTrue(account1.getCurrentBalace()==2100);
    }


    @Test
    public void testTransfer_InsufficientFundsException()   {
        Assertions.assertThrows(ServiceException.class, () -> {
            MonenyTransfer mt = new MonenyTransfer();
            mt.setAmount(3000);
            mt.setFromAccountNumber(accountID_1);
            mt.setToAccountNumber(accountID_2);
            RevoluteBankService.getInstance().transferMoney(mt);
        });
    }

    @Test
    public void testFetchInvalidAccountNumber() throws Exception {
        Assertions.assertThrows(ServiceException.class, () -> {
            uk.revolute.bank.domain.Account account = RevoluteBankService.getInstance().getAccountSummary(0);
          });
    }

    @Test
    public void testAccountActive() throws Exception {
       uk.revolute.bank.domain.Account account = RevoluteBankService.getInstance().getAccountSummary(accountID_1);
       Assertions.assertTrue(account.isActive());
       Assertions.assertTrue(account.getAccountHolderName().equalsIgnoreCase("Ganesha"));
    }


}