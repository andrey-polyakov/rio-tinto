package com.avinty.hr.service;

import com.avinty.hr.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final EmployeeEntity employeeEntity = employeeRepository.findByEmail(email);
        if(employeeEntity == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employeeEntity.getEmail(), employeeEntity.getPassword(), getAuthority(employeeEntity));
    }

    private Set<SimpleGrantedAuthority> getAuthority(final EmployeeEntity employeeEntity) {
        final String[] roles = employeeEntity.getRoles().split(";");
        return Arrays.stream(roles).map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toSet());
    }

}