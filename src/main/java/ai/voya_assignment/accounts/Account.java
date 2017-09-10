package ai.voya_assignment.accounts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private long balance;
    @Column
    private long userId;
    @Column(name = "creation_time")
    private long creationTime;

    public Account() {
        creationTime = System.currentTimeMillis();
    }
    
    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public long getUserId() {
        return userId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    
    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    
    public void alterBalance(long amount){
        balance+=amount;
    }
    
}
