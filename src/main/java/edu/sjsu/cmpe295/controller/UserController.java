package edu.sjsu.cmpe295.controller;

import edu.sjsu.cmpe295.domains.User;
import edu.sjsu.cmpe295.repository.UserRepository;
import edu.sjsu.cmpe295.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by BladeInShine on 16/2/25.
 */

@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody User user){
        User queryUser = userRepository.findOneByEmail(user.getEmail());
        if(queryUser != null) return new ResponseEntity<>("Email address already in use", HttpStatus.BAD_REQUEST);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
