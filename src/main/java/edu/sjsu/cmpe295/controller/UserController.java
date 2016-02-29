package edu.sjsu.cmpe295.controller;

import edu.sjsu.cmpe295.domains.User;
import edu.sjsu.cmpe295.repository.UserRepository;
import edu.sjsu.cmpe295.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by BladeInShine on 16/2/25.
 */

// http://localhost:8081/user/

@RestController
//@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserRepository userRepository;


    @Inject
    private UserService userService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String testGET(@PathVariable("id") String id, Object U) {
        System.out.print(U.toString());
        return "IN User Controller , hello world, get success";
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String testPOST(@RequestBody User U) {
        System.out.print(U.toString());
        return "IN User Controller , hello world, post";
    }


    //  /user/register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(/*@Valid */@RequestBody User user) {
        User queryUser = userRepository.findOneByEmail(user.getEmail());
        if (queryUser != null) return new ResponseEntity<>("Email address already in use", HttpStatus.BAD_REQUEST);
        userService.createUser(user);
        System.out.print("Register success");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
