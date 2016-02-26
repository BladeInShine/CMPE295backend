package edu.sjsu.cmpe295.security;

import edu.sjsu.cmpe295.domains.User;
import edu.sjsu.cmpe295.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BladeInShine on 16/2/25.
 */
@Component("userDetailsService")
public class UserDetailedService implements org.springframework.security.core.userdetails.UserDetailsService{

    private final Logger log = LoggerFactory.getLogger(UserDetailedService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.debug("Authenticating {}", email);
        String lowercaseEmail = email.toLowerCase();
        User user = userRepository.findOneByEmail(lowercaseEmail);
        if(user == null)
            throw new UsernameNotFoundException(String.format("User with email=%s was not found", email));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);

    }
}
