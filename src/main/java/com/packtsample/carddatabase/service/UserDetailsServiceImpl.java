package com.packtsample.carddatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.packtsample.carddatabase.domain.AppUser;
import com.packtsample.carddatabase.domain.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // private final AppUserRepository repository;

    // public UserDetailsServiceImpl(AppUserRepository repository) {
    //     this.repository = repository;
    // }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     // TODO Auto-generated method stub
    //     Optional<AppUser> user = repository.findByUsername(username);
    //     UserBuilder builder = null;
    //     if (user.isPresent()) {
    //         AppUser currentUser = user.get();
    //         builder = org.springframework.security.core.userdetails.User.withUsername(username);
    //         builder.password(currentUser.getPassword());
    //         builder.roles(currentUser.getRole());
    //     } else {
    //         throw new UsernameNotFoundException(
    //                 "User not found.");
    //     }
    //     return builder.build();
       
    // }

     @Autowired
    private AppUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return user; // Ensure AppUser implements UserDetails
    }

}
