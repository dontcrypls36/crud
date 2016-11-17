package com.pozdnyakov.dao;

import com.pozdnyakov.model.Person;

import java.util.List;

/**
 * Created by Artem on 21.02.2016.
 */
public interface PersonDAO {
    int create(Person p);
    void deleteAll();
    void delete(int id);
    void update(Person e);
    List<Person> read(Long page, int personsOnPage);
    List<Person> findByName(String name, Long page, int personsOnPage);
    Person getPersonById(int id);
    int getPersonsCount();
    int getPersonsCount(String name);
    Person findByLogin(String name);
}
