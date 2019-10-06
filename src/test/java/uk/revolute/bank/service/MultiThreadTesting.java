package uk.revolute.bank.service;

import uk.revolute.bank.dto.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.service.RevoluteBankService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class MultiThreadTesting {

  public static void main(String[] args) throws Exception {
      createAccounts();
      ExecutorService es = Executors.newFixedThreadPool(5);


          es.execute(() -> {
              try {
                  moneyTransfer1();
              } catch (Exception e) {
                  System.out.println("-- exception thrown during moneyTransfer 1 --");
                  e.printStackTrace();
              }
          });
          es.execute(() -> {
              try {
                  moneyTransfer2();
              } catch (Exception e) {
                  System.out.println("-- exception thrown during moneyTransfer 2 --");
                  e.printStackTrace();
              }
          });
          es.execute(() -> {
              try {
                  moneyTransfer1();
              } catch (Exception e) {
                  System.out.println("-- exception thrown during moneyTransfer 3 --");
                  e.printStackTrace();
              }
          });
          es.execute(() -> {
              try {
                  moneyTransfer2();
              } catch (Exception e) {
                  System.out.println("-- exception thrown during moneyTransfer 4 --");
                  e.printStackTrace();
              }
          });

          es.execute(() -> {
              try {
                  moneyTransfer1();
              } catch (Exception e) {
                  System.out.println("-- exception thrown during moneyTransfer 5  --");
                  e.printStackTrace();
              }
          });
          es.shutdown();
          //wait for the threads to finish
          try {
              es.awaitTermination(5, TimeUnit.SECONDS);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          //getAccountSummmary();


  }

    static long accountID_1 =0;
    static long accountID_2 =0;
  private static void  createAccounts() throws Exception {
      Account account1 = new Account();
      account1.setCurrentBalace(100);
      account1.setActive(true);
      account1.setBranch("Leeds");
      account1.setAccountHolderName("Ganesha");
      account1.setAccountHolderDOB(LocalDate.of(1991, 6, 30));
      account1.setCreatedDate(LocalDateTime.now());
      account1.setUpdatedDate(LocalDateTime.now());
      accountID_1=  RevoluteBankService.getInstance().createAccounts(account1);

      Account account2 = new Account();
      account2.setCurrentBalace(100);
      account2.setActive(true);
      account2.setBranch("London");
      account2.setAccountHolderName("Shiva");
      account2.setAccountHolderDOB(LocalDate.of(1998, 6, 30));
      account2.setCreatedDate(LocalDateTime.now());
      account2.setUpdatedDate(LocalDateTime.now());
      accountID_2=  RevoluteBankService.getInstance().createAccounts(account2);
  }

  private static void moneyTransfer1() throws Exception {
      System.out.println("Update moneyTransfer 1 starts............");
      MonenyTransfer mt= new MonenyTransfer();
      mt.setAmount(50);
      mt.setFromAccountNumber(accountID_1);
      mt.setToAccountNumber(accountID_2);
      RevoluteBankService.getInstance().transferMoney(mt);
  }

  private static void moneyTransfer2() throws Exception {
      System.out.println("Update moneyTransfer 2 starts............");
      MonenyTransfer mt= new MonenyTransfer();
      mt.setAmount(50);
      mt.setFromAccountNumber(accountID_1);
      mt.setToAccountNumber(accountID_2);
      RevoluteBankService.getInstance().transferMoney(mt);
  }



}