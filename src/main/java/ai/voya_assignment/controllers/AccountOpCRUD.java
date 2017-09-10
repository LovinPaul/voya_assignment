package ai.voya_assignment.controllers;

import ai.voya_assignment.accounts.Account;
import ai.voya_assignment.users.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountOpCRUD extends Controller {


    
    @RequestMapping(value="/newAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public Object addNewAccountToUser(@RequestParam String email) {
        User userInDB = database.getUser(email);
        if (userInDB != null) {
            Account account = new Account();
            if(!database.addObject(account))return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            userInDB.addAccount(account);
            if(!database.updateObject(account))return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            if(!database.updateObject(userInDB))return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            return new Object[]{HTTP_OK, account};
        } else {
            return HTTP_NOT_FOUND + USER_DOES_NOT_EXIST;
        }
    }

    @RequestMapping(value="/getAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getAccount(@RequestParam String email, @RequestParam int index) {
        User userInDB = database.getUser(email);
        if (userInDB != null) {
            long accountID = userInDB.getAccountID(index);
            if (accountID != -1) {
                Account account = database.getAccount(accountID);
                if(account!=null){
                    return new Object[]{HTTP_OK, account};
                }else{
                    return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
                }
            } else {
                return HTTP_BAD_REQUEST + INVALID_INDEX;
            }
        } else {    
            return HTTP_NOT_FOUND + USER_DOES_NOT_EXIST;
        }
    }
    
    //editAccount
    //deleteAccount
}
