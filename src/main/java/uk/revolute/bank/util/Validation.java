package uk.revolute.bank.util;

import uk.revolute.bank.domain.Account;
import uk.revolute.bank.exception.ServiceException;

public class Validation {

    public  static boolean isActive(Account account) throws ServiceException {
        if(!account.isActive()){
            return false;
        }
        return  true;
    }

    public static boolean isAccountHasSufficientFund(double balance, double amount) throws ServiceException {
        if (balance < amount ) {
          return false;
        }
        return  true;
    }




}
