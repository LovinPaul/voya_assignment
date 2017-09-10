package ai.voya_assignment.controllers;

import ai.voya_assignment.accounts.Account;
import ai.voya_assignment.users.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOpCRUD extends Controller{
    

    
    @RequestMapping(value="/newUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object newUser(@RequestParam String name, @RequestParam String email) {
        User userInDB = database.getUser(email);
        if (userInDB == null) {
            Account account = new Account();
            User user = new User(name, email);
            if(!database.addObject(account)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            if(!database.addObject(user)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            user.addAccount(account);
            if(!database.updateObject(account)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            if(!database.updateObject(user)) return HTTP_INTERNAL_SERVER_ERROR + DATABASE_ERROR;
            return new Object[]{HTTP_OK, user};
        } else {
            return HTTP_CONFLICT + email + EMAIL_ALREADY_EXISTS;
        }
    }

    @RequestMapping(value="/getUser", method = {RequestMethod.POST, RequestMethod.GET})
    public Object getUser(@RequestParam String email) {
        User user = database.getUser(email);
        if(user==null){
            return HTTP_NOT_FOUND + USER_DOES_NOT_EXIST;
        }else{
            return new Object[]{HTTP_OK, user};
        }
    }
    
    //editUser
    //deleteUser
}
