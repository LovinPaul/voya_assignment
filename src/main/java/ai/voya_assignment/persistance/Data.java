package ai.voya_assignment.persistance;

import ai.voya_assignment.accounts.Account;
import ai.voya_assignment.users.*;

public interface Data {
    boolean addObject(Object object);
    boolean updateObject(Object object);
    User getUser(String email);
    Account getAccount(long id);
}
