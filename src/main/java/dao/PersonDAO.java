package dao;

import model.User;

import java.util.List;

/**
 * Created by Artem on 21.02.2016.
 */
public interface PersonDAO {
    int create(User p);
    void deleteAll();
    void delete(int id);
    void update(User e);
    List<User> read(Long page, int personsOnPage);
    List<User> findByName(String name, Long page, int personsOnPage);
    User getPersonById(int id);
    int getPersonsCount();
    int getPersonsCount(String name);
}
