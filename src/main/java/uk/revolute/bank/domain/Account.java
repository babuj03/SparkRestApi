package uk.revolute.bank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Account extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String accountHolderName;
    private LocalDate accountHolderDOB;
    private double currentBalace;
    private String branch;

    public double getCurrentBalace() {
        return currentBalace;
    }

    public void setCurrentBalace(double currentBalace) {
        this.currentBalace = currentBalace;
    }

    public String getBranch() {
        return branch;
    }



    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public LocalDate getAccountHolderDOB() {
        return accountHolderDOB;
    }

    public void setAccountHolderDOB(LocalDate accountHolderDOB) {
        this.accountHolderDOB = accountHolderDOB;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderDOB=" + accountHolderDOB +
                ", currentBalace=" + currentBalace +
                ", branch='" + branch + '\'' +
                '}';
    }
}
