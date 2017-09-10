package ai.voya_assignment.users;

import ai.voya_assignment.accounts.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User  implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> accounts;
    @Column(name = "creation_time")
    private long creationTime;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        creationTime = System.currentTimeMillis();
        accounts = new ArrayList<>();
    }
    
    public void addAccount(Account account){
        account.setUserId(id);
        accounts.add(account.getId());
    }
    
    public long getAccountID(int index){
        if(index>=accounts.size()) return -1;
        Collections.sort(accounts);
        return accounts.get(index);
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getCreationTime() {
        return creationTime;
    }

    
    
    
}
