package dao;


import mysql.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

        private PersonDAO personDAO;

        public void setPersonDAO(PersonDAO p){
            this.personDAO = p;
        }
        @Transactional
        public void create(User p) {
            personDAO.create(p);
        }
        @Transactional
        public List<User> read(Long page, int personsOnPage) {
            return personDAO.read(page, personsOnPage);
        }
        @Transactional
        public List<User> findByName(String name, Long page, int personsOnPage){ return personDAO.findByName(name, page, personsOnPage);}
        @Transactional
        public void update(User p) {
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
        public User getPersonById(int id){
            return personDAO.getPersonById(id);
        }

        @Transactional
        public int getPersonsCount() {
            return personDAO.getPersonsCount();
        }

        @Transactional
        public int getPersonsCount(String name) {return personDAO.getPersonsCount(name);}
}
