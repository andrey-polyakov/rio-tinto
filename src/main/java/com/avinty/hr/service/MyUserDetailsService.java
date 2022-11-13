package com.avinty.hr.service;

import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeDao;
import com.avinty.hr.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        EmployeeEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}