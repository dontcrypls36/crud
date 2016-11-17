package com.pozdnyakov.service;


import com.pozdnyakov.dao.PersonDAO;
import com.pozdnyakov.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

        @Autowired
        private PersonDAO personDAO;

        public void setPersonDAO(PersonDAO p){
            this.personDAO = p;
        }
        @Transactional
        public void create(Person p) {
            personDAO.create(p);
        }
        @Transactional
        public List<Person> read(Long page, int personsOnPage) {
            return personDAO.read(page, personsOnPage);
        }
        @Transactional
        public List<Person> findByName(String name, Long page, int personsOnPage){ return personDAO.findByName(name, page, personsOnPage);}
        @Transactional
        public void update(Person p) {
            personDAO.update(p);
        }
        @Transactional
        public void delete(int id) {
            personDAO.delete(id);
        }
        @Transactional
        public void deleteAll() {
            personDAO.deleteAll();
        }

        @Transactional
        public Person getPersonById(int id){
            return personDAO.getPersonById(id);
        }

        @Transactional
        public int getPersonsCount() {
            return personDAO.getPersonsCount();
        }

        @Transactional
        public int getPersonsCount(String name) {return personDAO.getPersonsCount(name);}

        public Person findByLogin(String s){return personDAO.findByLogin(s);}
}
