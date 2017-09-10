package ai.voya_assignment.persistance;

import ai.voya_assignment.accounts.Account;
import ai.voya_assignment.users.User;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MySQLDatabase implements Data {

    private static final MySQLDatabase INSTACE = new MySQLDatabase();
    private static final Connection HIBERNATE = new Connection();

    private MySQLDatabase() {
    }

    public static MySQLDatabase getINSTACE() {
        return INSTACE;
    }

    @Override
    public boolean addObject(Object object) {
        return HIBERNATE.addObject(object);
    }

    @Override
    public boolean updateObject(Object object) {
        return HIBERNATE.updateObject(object);
    }

    @Override
    public User getUser(String email) {
        String hql = "from User u where u.email='" + email + "'";
        return (User) HIBERNATE.getObject(hql);
    }

    @Override
    public Account getAccount(long id) {
        String hql = "from Account a where a.id=" + id;
        return (Account) HIBERNATE.getObject(hql);
    }

    private static class Connection {

        private static final SessionFactory FACTORY = BuildFactory();

        private static SessionFactory BuildFactory() {
            try {
                return new Configuration().configure().buildSessionFactory();
            } catch (HibernateException ex) {
                System.err.println("Failed to create sessionFactory object. Error message : " + ex.getMessage());
                throw new ExceptionInInitializerError(ex);
            }
        }

        public boolean addObject(Object object) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(object);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
            return true;
        }

        public boolean updateObject(Object object) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(session.merge(object));
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
                return false;
            } finally {
                session.close();
            }
            return true;
        }

        public Object getObject(String hql) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            Object object = null;
            try {
                tx = session.beginTransaction();
                object = session.createQuery(hql).uniqueResult();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
            return object;
        }
    }

}
