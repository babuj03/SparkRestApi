package uk.revolute.bank.service;

import org.apache.commons.beanutils.BeanUtils;
import uk.revolute.bank.dao.RevoluteBankDAO;
import uk.revolute.bank.domain.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.exception.ServiceException;
import uk.revolute.bank.util.ServiceMessage;
import uk.revolute.bank.util.Validation;

import java.util.List;

public class RevoluteBankService {

    private static final RevoluteBankService SINGLE_INSTANCE = new RevoluteBankService();

    public static RevoluteBankService getInstance() {
        return SINGLE_INSTANCE;
    }


    public synchronized void transferMoney(MonenyTransfer transfer) throws ServiceException, Exception {
        Account fromAccount = getAccountSummary(transfer.getFromAccountNumber());
        Account toAccount = getAccountSummary(transfer.getToAccountNumber());
        if (!Validation.isAccountHasSufficientFund(fromAccount.getCurrentBalace(), transfer.getAmount())) {
            throw new ServiceException( ServiceMessage.INSUFFICIENT_FUND.getDescription());
        }
        if (!Validation.isActive(fromAccount) || !Validation.isActive(toAccount)) {
            throw new ServiceException( ServiceMessage.ACCOUNT_INACTIVE.getDescription());
        }
        RevoluteBankDAO.getInstance().transferMoney(transfer);
    }

    public Account getAccountSummary(long accountNumber) throws ServiceException, Exception {
        Account accountDto = RevoluteBankDAO.getInstance().getAccountSummary(accountNumber);
        if(accountDto == null)
            throw new ServiceException(ServiceMessage.INVALID_ACCOUNT_NUMBER.getDescription());
        return accountDto;
    }

    public Long createAccounts(uk.revolute.bank.dto.Account account) throws ServiceException, Exception {
       return RevoluteBankDAO.getInstance().getCreateAccounts(account);
    }
}
