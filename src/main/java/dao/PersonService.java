package dao;

import mysql.User;

import java.util.List;

/**
 * Created by Artem on 23.02.2016.
 */
public interface PersonService {
    void create(User p);
    List<User> read(Long page, int personsOnPage);
    List<User> findByName(String name, Long page, int personsOnPage);
    void update(User p);
    void delete(int id);
    void deleteAll();
    User getPersonById(int id);
    int getPersonsCount();
    int getPersonsCount(String name);
}
