package uk.revolute.bank.resource;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
//import uk.revolute.bank.dto.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.revolute.bank.domain.Account;
import uk.revolute.bank.dto.MonenyTransfer;
import uk.revolute.bank.exception.ServiceException;
import uk.revolute.bank.service.RevoluteBankService;
import uk.revolute.bank.util.JsonTransformer;
import uk.revolute.bank.util.ServiceMessage;

import static spark.Spark.get;


import static spark.Spark.*;

/**
 * @author JBabu
 * @Created Date : 04/10/2019
 */
public class BankAccountResource {

    public static void main(String[] args) {
        RevoluteBankService service = RevoluteBankService.getInstance();
        Logger log = LoggerFactory.getLogger(BankAccountResource.class);
        get("/account/summary/:accountNumber", (req, res) -> {
            String accountNumberStr = req.params(":accountNumber");
            if (accountNumberStr == null
                    || accountNumberStr.isEmpty()
                    || Long.parseLong(accountNumberStr) <=0 ) {
                res.status(HttpStatus.UNPROCESSABLE_ENTITY_422);
                return new ServiceException(
                        ServiceMessage.INVALID_ACCOUNT_NUMBER.getDescription());
            }

            try {
                return service.getAccountSummary( Long.parseLong(accountNumberStr));
            } catch (ServiceException ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return ex.getMessage();
            } catch (Exception ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return   ServiceMessage.GENERAL_ERROR.getDescription();
            }
        }, new JsonTransformer());


        post("/create/account", (req, res) -> {
            try {
                uk.revolute.bank.dto.Account account = new Gson().fromJson(req.body().toString(),  uk.revolute.bank.dto.Account.class);
                Long accId =  service.createAccounts(account);
                return ServiceMessage.ACCOUNT_CREATE_SUCCESS.getDescription() +":"+accId;
            } catch (ServiceException ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return ex.getMessage();
            } catch (Exception ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return new ServiceException(
                        ServiceMessage.GENERAL_ERROR.getDescription());
            }
        }, new JsonTransformer());


        put("/money/transfer", (req, res) -> {
            try {
                MonenyTransfer monenyTransfer = new Gson().fromJson(req.body(), MonenyTransfer.class);
                service.transferMoney(monenyTransfer);
                return ServiceMessage.TRANSFER_SUCCESS.getDescription();
            } catch (ServiceException ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return ex.getMessage();
            } catch (Exception ex) {
                log.error("Error :"+ex.getMessage());
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
                return  ServiceMessage.GENERAL_ERROR.getDescription();
            }
        }, new JsonTransformer());

    }
}
