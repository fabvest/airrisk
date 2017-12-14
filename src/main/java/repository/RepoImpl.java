package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoImpl implements RepoInterface {
    public RepoImpl() {
    }

    @Override
    public boolean addObject(Object emp) throws SQLException {
        Session s = null;

        Transaction tr = null;
        try {
            s = HibernateUtil.getSession();
            tr = s.beginTransaction();
//            s.beginTransaction();
            s.save(emp);
//            s.getTransaction().commit();
            tr.commit();
            return true;

        } catch (Exception e) {
            return false;

        } finally {
            if (s != null && s.isOpen()) {
                s.close();
            }
        }
    }

    @Override
    public Object getObject(Class clazz, Long id) throws SQLException {
        Session s = null;
        Transaction tx;
        Object o = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            o = s.get(clazz, id);
            tx.commit();
        }catch (Exception e){
            System.out.println("error with get event by id");
        }finally {
            if(s != null && s.isOpen()) {
                s.close();
            }
        }
        return o;
    }

    @Override
    public List<Object> getAllObjects(Class clazz) throws SQLException {
        Session session = null;
        Transaction tx = null;
        List ev = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            ev = session.createCriteria(clazz).list();
            tx.commit();
        } catch (Exception e) {
            System.out.println("error with get all ev types");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return ev;
    }
}
