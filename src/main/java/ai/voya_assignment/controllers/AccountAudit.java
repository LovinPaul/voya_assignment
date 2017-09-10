package ai.voya_assignment.controllers;

import ai.voya_assignment.accounts.Account;
import ai.voya_assignment.users.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountAudit extends Controller {



    @RequestMapping(value="/withdraw", method = {RequestMethod.POST, RequestMethod.GET})
    public Object withdraw(@RequestParam String email, @RequestParam int index, @RequestParam int amount) {
        Account account = getAccount(email, index);
        if (account != null) {
            if (account.getBalance() >= amount) {
                if(!alterBalanceUpdateAccount(account, -amount)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
                return new Object[]{HTTP_OK, account};
            } else {
                return HTTP_BAD_REQUEST + NOT_ENOUGH_MONEY;
            }
        } else {
            return HTTP_BAD_REQUEST + ACCOUNT_UNAVAILABLE;
        }
    }
    
    @RequestMapping(value="/deposit", method = {RequestMethod.POST, RequestMethod.GET})
    public Object deposit(@RequestParam String email, @RequestParam int index, @RequestParam int amount) {
        Account account = getAccount(email, index);
        if (account != null) {
            if(!alterBalanceUpdateAccount(account, amount)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            return new Object[]{HTTP_OK, account};
        } else {
            return HTTP_BAD_REQUEST + ACCOUNT_UNAVAILABLE;
        }
    }
    
    @RequestMapping(value="/transfer", method = {RequestMethod.POST, RequestMethod.GET})
    public Object transfer(@RequestParam String email, @RequestParam int fromIndex, @RequestParam int toIndex, @RequestParam int amount) {
        Account fromAccount = getAccount(email, fromIndex);
        Account toAccount = getAccount(email, toIndex);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                if(!alterBalanceUpdateAccount(fromAccount, -amount)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
                if(!alterBalanceUpdateAccount(toAccount, amount)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
                return new Object[]{HTTP_OK, fromAccount, toAccount};
            } else {
                return HTTP_BAD_REQUEST + NOT_ENOUGH_MONEY;
            }
        } else {
            return HTTP_BAD_REQUEST + ACCOUNT_UNAVAILABLE;
        }
    }
    
    @RequestMapping(value="/balance", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getBalance(@RequestParam String email, @RequestParam int index) {
        Account account = getAccount(email, index);
        if (account != null) {
            return new Object[]{HTTP_OK, account.getBalance()};
        } else {
            return HTTP_BAD_REQUEST + ACCOUNT_UNAVAILABLE;
        }
    }
    
    private Account getAccount(String email, int index) {
        User userInDB = database.getUser(email);
        if (userInDB != null) {
            long accountID = userInDB.getAccountID(index);
            if (accountID != -1) {
                return database.getAccount(accountID);
            }
        }
        return null;
    }
    
    private boolean alterBalanceUpdateAccount(Account account, int amount) {
        account.alterBalance(amount);
        return database.updateObject(account);
    }

}
