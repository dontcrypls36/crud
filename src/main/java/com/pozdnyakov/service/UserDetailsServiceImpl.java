package com.pozdnyakov.service;

import com.pozdnyakov.model.Person;
import com.pozdnyakov.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonService personService;

    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        Person p = personService.findByLogin(s);
        return new User(p.getLogin(), p.getPassword(), true, true, true, true, getGrantedAuthorities(p));
    }

    @Autowired
    public UserDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Person p){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : p.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        return authorities;
    }
}
