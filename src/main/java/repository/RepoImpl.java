package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.sql.SQLException;

public class RepoImpl implements RepoInterface {
    public RepoImpl() {
    }

    @Override
    public boolean addObject(Object emp) throws SQLException {
        Session s = null;

        Transaction tr = null;
        try {
            s = HibernateUtil.getSession();
//            tr = s.beginTransaction();
            s.beginTransaction();
            s.save(emp);
            s.getTransaction().commit();
//            tr.commit();
            return true;

        } catch (Exception e) {
            return false;

        } finally {
            if (s != null && s.isOpen()) {
                s.close();
            }
        }
    }
}
