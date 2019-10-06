package uk.revolute.bank.dao;

import org.apache.commons.beanutils.BeanUtils;
import uk.revolute.bank.domain.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.exception.ServiceException;
import uk.revolute.bank.util.ObjectMapper;
import uk.revolute.bank.util.ServiceMessage;
import uk.revolute.bank.util.Validation;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class  RevoluteBankDAO {
    private static final RevoluteBankDAO SINGLE_INSTANCE = new RevoluteBankDAO();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("REVOLUTE_BANK");

    public static RevoluteBankDAO getInstance() {
        return SINGLE_INSTANCE;
    }

    public Account getAccountSummary(Long accountNumber) {
        EntityManager entityManager = emf.createEntityManager();
        Account account = null;
        entityManager.getTransaction().begin();
        account = entityManager.find(Account.class, accountNumber);
        entityManager.getTransaction().commit();
        return account;
    }


    public void transferMoney(MonenyTransfer transfer) throws Exception{
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Account fromAccount = (Account) entityManager.find(Account.class, transfer.getFromAccountNumber());
            fromAccount.setCurrentBalace(withdraw(fromAccount.getCurrentBalace(),transfer.getAmount()));
            Account toAccount = (Account) entityManager.find(Account.class, transfer.getToAccountNumber());
            toAccount.setCurrentBalace(deposit(toAccount.getCurrentBalace(), transfer.getAmount()));
            entityManager.merge(fromAccount);
            entityManager.merge(toAccount);
            entityManager.getTransaction().commit();
        }catch(ServiceException ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }catch(Exception ex){
             entityManager.getTransaction().rollback();
             throw ex;
         }
    }


    public double deposit(double balance, double amount) {
        balance = balance + amount;
        return balance;
    }

    public double withdraw(double balance, double amount) throws ServiceException {
        if(!Validation.isAccountHasSufficientFund(balance,amount)) {
            throw new ServiceException(ServiceMessage.INSUFFICIENT_FUND.getDescription());
        }
        balance = balance - amount ;
        return balance;
    }

    public long getCreateAccounts(uk.revolute.bank.dto.Account account) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        try {
           entityManager.getTransaction().begin();
            Account accEntity = new Account();
            ObjectMapper.copyProperties(account,accEntity);
            entityManager.persist(accEntity);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return accEntity.getAccountNumber();
        } catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }

    }

}