package repository;

import model.Result;
import model.Substance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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

    @Override
    public ArrayList<Substance> getSubByReport(long id) throws SQLException {
        Session s = null;
        ArrayList<Substance> l = null;
        try {
            s = HibernateUtil.getSession();
            Query query = s.createQuery("from Substance where id = :id");
            query.setParameter("id", id);
            l = (ArrayList<Substance>) query.list();
        }catch (Exception e){
            System.out.println("error with get event by email");
        }finally {
            if(s != null && s.isOpen()) {
                s.close();
            }
        }
        return l;
    }

    @Override
    public ArrayList<Result> getResByReport(long id) throws SQLException {
        Session s = null;
        ArrayList<Result> l = null;
        try {
            s = HibernateUtil.getSession();
            Query query = s.createQuery("from Result where id = :id");
            query.setParameter("id", id);
            l = (ArrayList<Result>) query.list();
        }catch (Exception e){
            System.out.println("error with get event by email");
        }finally {
            if(s != null && s.isOpen()) {
                s.close();
            }
        }
        return l;
    }


}
