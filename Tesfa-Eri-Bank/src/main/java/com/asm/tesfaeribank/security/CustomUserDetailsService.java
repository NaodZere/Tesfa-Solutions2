package com.asm.tesfaeribank.security;

import com.asm.tesfaeribank.domain.Role;
import com.asm.tesfaeribank.domain.User;
import com.asm.tesfaeribank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not Found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),mapRoles(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoles(List<Role> roles) {
        return roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
