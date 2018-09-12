package tech.valery.presentation.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.valery.presentation.service.UserService;



@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        tech.valery.presentation.model.User user = userService.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Cannot find user by email: " + email);
        }

        return User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.getRole().getRole())
                .build();
    }
}
