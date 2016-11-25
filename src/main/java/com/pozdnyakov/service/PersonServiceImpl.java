package com.pozdnyakov.service;


import com.pozdnyakov.dao.PersonDAO;
import com.pozdnyakov.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

        @Autowired
        private PersonDAO personDAO;

        public void setPersonDAO(PersonDAO p){
            this.personDAO = p;
        }

        public void create(Person p) {
            personDAO.create(p);
        }

        public List<Person> read(Long page, int personsOnPage) {
            return personDAO.read(page, personsOnPage);
        }

        public List<Person> findByName(String name, Long page, int personsOnPage){ return personDAO.findByName(name, page, personsOnPage);}

        public void update(Person p) {
            personDAO.update(p);
        }

        public void delete(int id) {
            personDAO.delete(id);
        }

        public void deleteAll() {
            personDAO.deleteAll();
        }


        public Person getPersonById(int id){
            return personDAO.getPersonById(id);
        }


        public int getPersonsCount() {
            return personDAO.getPersonsCount();
        }


        public int getPersonsCount(String name) {return personDAO.getPersonsCount(name);}

        public Person findByLogin(String s){return personDAO.findByLogin(s);}
}
