package Controlador.dao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author
 */
public abstract class AbstractDao {

    protected Session session;
    protected Transaction tx;

    public AbstractDao() {
        HibernateFactory.buildIfNeeded();
    }

    protected void save(Object obj) {
        try {
            startOperation();
            session.persist(obj);
            tx.commit();
        } catch (HibernateException e) {
            System.err.println("No paso de aqui");
            //handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    protected void update(Object obj) {
        try {
            startOperation();
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

    protected void delete(Object obj) {
        try {
            startOperation();
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
    }

   
    protected List findAll(Class clazz) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return objects;
    }
    protected Object find(Class clazz, int id , String campo) {
        Object obj = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName() + " where "+campo+" = "+id+"");
            //query.setParameter("code", id);
            obj=query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return obj;
    }
     protected Object find(Class clazz, String id , String campo) {
        Object obj = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName() + " where "+campo+" = "+id+"");
            //query.setParameter("code", id);
            obj=query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactory.close(session);
        }
        return obj;
    }

    protected void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    protected void startOperation() throws HibernateException {
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
    }
    
     
}
