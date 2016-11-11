package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Artem on 21.02.2016.
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int create(User p) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Integer i = (Integer)session.save(p);
        tr.commit();
        session.close();
        return p.getId();
    }

    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User p = (User) session.load(User.class, id);
        session.delete(p);
        tx.commit();
        session.close();
    }

    public void update(User e) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User em = (User) session.get(User.class, e.getId());
        em.setName(e.getName());
        em.setAge(e.getAge());
        em.setAdmin(e.isAdmin());
        em.setCreateDate(e.getCreateDate());
        session.getTransaction().commit();
        session.close();
    }

    public List<User> read(Long page, int personsOnPage) {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        Query q = session.createQuery("FROM User");
        q.setFirstResult((page.intValue() - 1) * personsOnPage);
        q.setMaxResults( personsOnPage);
        List<User> l = q.list();
        session.close();
        return l;
    }

    public User getPersonById(int id){
        Session session = sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        return p;
    }

    public List<User> findByName(String name, Long page, int personsOnPage) {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        Query q = session.createQuery("FROM User WHERE name = :name");
        q.setParameter("name", name);
        q.setFirstResult((page.intValue() - 1) *  personsOnPage);
        q.setMaxResults( personsOnPage);
        List<User> l = q.list();
        session.close();
        return l;
    }

    public int getPersonsCount() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM User");
        int count = q.list().size();
        session.close();
        return count;
    }

    public int getPersonsCount(String name) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM User WHERE name = :name");
        q.setParameter("name", name);
        int count = q.list().size();
        session.close();
        return count;
    }
}
