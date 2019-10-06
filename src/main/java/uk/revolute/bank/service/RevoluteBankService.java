package uk.revolute.bank.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import uk.revolute.bank.dao.RevoluteBankDAO;
import uk.revolute.bank.domain.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.exception.ServiceException;
import uk.revolute.bank.util.ServiceMessage;
import uk.revolute.bank.util.Validation;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class RevoluteBankService {

    private static final RevoluteBankService SINGLE_INSTANCE = new RevoluteBankService();

    public static RevoluteBankService getInstance() {
        return SINGLE_INSTANCE;
    }

    // Distributed Locking
    ILock lock =null;
    Config cfg = new Config();
    HazelcastInstance hz = Hazelcast.newHazelcastInstance(cfg);

    public  void transferMoney(MonenyTransfer transfer) throws Exception {
        lock = hz.getLock("TransferLock");
        if (lock.tryLock (5000, TimeUnit.MILLISECONDS)) {
            try {
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
            finally {
               lock.unlock();
            }
       }
    }

    public Account getAccountSummary(long accountNumber) throws Exception {
        Account accountDto = RevoluteBankDAO.getInstance().getAccountSummary(accountNumber);
        if(accountDto == null)
            throw new ServiceException(ServiceMessage.INVALID_ACCOUNT_NUMBER.getDescription());
        return accountDto;
    }

    public Long createAccounts(uk.revolute.bank.dto.Account account) throws Exception {
       return RevoluteBankDAO.getInstance().getCreateAccounts(account);
    }
}
