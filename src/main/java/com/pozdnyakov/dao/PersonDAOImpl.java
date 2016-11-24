package com.pozdnyakov.dao;

import com.pozdnyakov.model.Person;
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

    public int create(Person p) {
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
        Query query = session.createQuery("DELETE FROM Person");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Person p = (Person) session.load(Person.class, id);
        session.delete(p);
        tx.commit();
        session.close();
    }

    public void update(Person e) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person em = (Person) session.get(Person.class, e.getId());
        em.setName(e.getName());
        em.setAge(e.getAge());
        em.setLogin(e.getLogin());
        em.setPassword(e.getPassword());
        em.setRoles(e.getRoles());
        em.setCreateDate(e.getCreateDate());
        session.getTransaction().commit();
        session.close();
    }

    public List<Person> read(Long page, int personsOnPage) {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        Query q = session.createQuery("FROM Person");
        q.setFirstResult((page.intValue() - 1) * personsOnPage);
        q.setMaxResults( personsOnPage);
        List<Person> l = q.list();
        session.close();
        return l;
    }

    public Person getPersonById(int id){
        Session session = sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        return p;
    }

    public List<Person> findByName(String name, Long page, int personsOnPage) {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        Query q = session.createQuery("FROM Person WHERE name = :name");
        q.setParameter("name", name);
        q.setFirstResult((page.intValue() - 1) *  personsOnPage);
        q.setMaxResults( personsOnPage);
        List<Person> l = q.list();
        session.close();
        return l;
    }

    public int getPersonsCount() {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Person");
        int count = q.list().size();
        session.close();
        return count;
    }

    public int getPersonsCount(String name) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Person WHERE name = :name");
        q.setParameter("name", name);
        int count = q.list().size();
        session.close();
        return count;
    }

    public Person findByLogin(String name) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Person WHERE login = :login");
        q.setParameter("login", name);
        return (Person)q.list().get(0);
    }
}
