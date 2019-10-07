package uk.revolute.bank.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class Account {
    private Long accountNumber;
    private String accountHolderId;
    private String accountHolderName;
    private LocalDate accountHolderDOB;
    private double currentBalace;
    private String branch;
    private boolean active;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate = LocalDateTime.now();

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", currentBalace=" + currentBalace +
                ", branch='" + branch + '\'' +
                ", active=" + active +
                ", accountHolderId ="+accountHolderId+
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderDOB='" + accountHolderDOB + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
