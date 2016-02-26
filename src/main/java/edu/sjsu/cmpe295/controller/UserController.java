package edu.sjsu.cmpe295.controller;

import edu.sjsu.cmpe295.domains.User;
import edu.sjsu.cmpe295.repository.UserRepository;
import edu.sjsu.cmpe295.service.UserService;
import org.apache.coyote.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by BladeInShine on 16/2/25.
 */

// http://localhost:8081/user/

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserRepository userRepository;


    @Inject
    private UserService userService;

//    @Autowired
//    public void UserController(UserService us){
//        this.userService = us;
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String test295() {
        return "test";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "hello world, home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        User queryUser = userRepository.findOneByEmail(user.getEmail());
        if (queryUser != null) return new ResponseEntity<>("Email address already in use", HttpStatus.BAD_REQUEST);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
