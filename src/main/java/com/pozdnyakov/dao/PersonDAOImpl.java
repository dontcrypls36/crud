package com.pozdnyakov.dao;

import com.pozdnyakov.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public int create(Person p) {
        Session session = sessionFactory.openSession();
        Integer i = (Integer)session.save(p);
        session.flush();
        session.close();
        return p.getId();
    }

    @Transactional
    public void deleteAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("DELETE FROM Person p where p.id != 99999");
        query.executeUpdate();
        session.close();
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Person p = (Person) session.load(Person.class, id);
        session.delete(p);
        session.flush();
        session.close();
    }

    @Transactional
    public void update(Person e) {
        Session session = sessionFactory.openSession();
        Person em = (Person) session.load(Person.class, e.getId());
        em.setName(e.getName());
        em.setAge(e.getAge());
        em.setLogin(e.getLogin());
        em.setPassword(e.getPassword());
        em.setRoles(e.getRoles());
        em.setCreateDate(e.getCreateDate());
        session.flush();
        session.close();
    }

    @Transactional
    public List<Person> read(Long page, int personsOnPage) {
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        Query q = session.createQuery("FROM Person order by id");
        q.setFirstResult((page.intValue() - 1) * personsOnPage);
        q.setMaxResults( personsOnPage);
        List<Person> l = q.list();
        session.close();
        return l;
    }

    @Transactional
    public Person getPersonById(int id){
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("SELECT u FROM Person u WHERE u.id = :id");
        q.setParameter("id", id);
        Person p = (Person) q.list().get(0);
//        Person p = (Person) session..load(Person.class, id);
        session.close();
        return p;
    }

    @Transactional
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

    @Transactional
    public Person findByLogin(String name) {
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM Person WHERE login = :login");
        q.setParameter("login", name);
        Person p = (Person)q.list().get(0);
        session.close();
        return p;
    }
}
