package com.pozdnyakov.service;

import com.pozdnyakov.model.Person;

import java.util.List;

/**
 * Created by Artem on 23.02.2016.
 */
public interface PersonService {
    void create(Person p);
    List<Person> read(Long page, int personsOnPage);
    List<Person> findByName(String name, Long page, int personsOnPage);
    Person findByLogin(String s);
    void update(Person p);
    void delete(int id);
    void deleteAll();
    Person getPersonById(int id);
    int getPersonsCount();
    int getPersonsCount(String name);
}
