package repository;

import model.Drugs;
import model.Report;
import model.Result;
import model.Substance;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoImpl<T> implements RepoInterface {
    public RepoImpl() {
    }

    @Override
    public boolean addObject(Object emp) throws SQLException {
        Session s = null;
        Transaction tr = null;
        try {
            s = HibernateUtil.getSession();
            tr = s.beginTransaction();
            s.save(emp);
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
            System.out.println("error with get some obj by id" + clazz.getName());
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println(e.getMessage());
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
            System.out.println("error with get all obj types");
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
            Query query = s.createQuery("from Substance where repo_id = :id");
            query.setParameter("id", id);
            l = (ArrayList<Substance>) query.list();
        }catch (Exception e){
            System.out.println("error with get subst by id");
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
            Query query = s.createQuery("from Result where repo_id = :id");
            query.setParameter("id", id);
            l = (ArrayList<Result>) query.list();
        }catch (Exception e){
            System.out.println("error with get results by pero_id");
        }finally {
            if(s != null && s.isOpen()) {
                s.close();
            }
        }
        return l;
    }

    @Override
    public ArrayList<Drugs> getDrugById(long id) throws SQLException {
        Session s = null;
        ArrayList<Drugs> l = null;
        try {
            s = HibernateUtil.getSession();
            Query query = s.createQuery("from Drugs where id = :id");
            query.setParameter("id", id);
            l = (ArrayList<Drugs>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            e.toString();
            e.getMessage();
            System.out.println("error with get drugs by id");
        }finally {
            if(s != null && s.isOpen()) {
                s.close();
            }
        }
        return l;
    }

    @Override
    public void updateObject(Object o) throws SQLException {
        Session s = null;
        Transaction tx = null;

        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(o);
            tx.commit();
        }catch (Exception e){
            System.out.println("err w update object");
        }finally {
            if(s != null && s.isOpen()){
                s.close();
            }
        }
    }
}
