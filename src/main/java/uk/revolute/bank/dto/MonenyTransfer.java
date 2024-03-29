package uk.revolute.bank.dto;
/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class MonenyTransfer {
    private Long fromAccountNumber;
    private Long toAccountNumber;
    private double amount;

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
