package com.pozdnyakov.service;

import com.pozdnyakov.model.Person;
import com.pozdnyakov.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonService personService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person p = personService.findByLogin(s);
        List<GrantedAuthority> authorities = buildUserAuthority(p.getRoles());
        return buildUserForAuthentication(p, authorities);
    }

    private User buildUserForAuthentication(Person person,
                                              List<GrantedAuthority> authorities) {
        return new User(person.getLogin(), person.getPassword(), true,
                 true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build person's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.name()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }
}
