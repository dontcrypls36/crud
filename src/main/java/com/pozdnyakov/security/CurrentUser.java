package com.pozdnyakov.security;

import com.pozdnyakov.model.Person;
import com.pozdnyakov.model.Role;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

public class CurrentUser extends User{

    private Person person;

    public CurrentUser(Person person) {
        super(person.getLogin(), person.getPassword(), AuthorityUtils.createAuthorityList(person.getRoles().toArray(new String[person.getRoles().size()])));
        this.person = person;
    }

    public Person getUser(){
        return person;
    }

    public Set<Role> getRoles(){
        return person.getRoles();
    }
}
