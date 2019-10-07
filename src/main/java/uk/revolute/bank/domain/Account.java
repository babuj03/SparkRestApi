package uk.revolute.bank.domain;

import javax.persistence.*;
import java.time.LocalDate;
/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"accountHolderId"})
})
public class Account extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String accountHolderId;
    private String accountHolderName;
    private LocalDate accountHolderDOB;
    private double currentBalace;
    private String baseCurrency ="GBP";
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

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderDOB=" + accountHolderDOB +
                ", currentBalace=" + currentBalace +
                ", branch='" + branch + '\'' +
                '}';
    }
}
