package com.proxsoftware.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxsoftware.webapp.Entity.Error;
import com.proxsoftware.webapp.Entity.User;
import com.proxsoftware.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.List;

/**
 * Created by Proxima on 10.07.2016.
 */

@RestController
@RequestMapping("api")
public class UserService extends AbstractController {
    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> login(@RequestBody User user) throws AuthenticationException {

        if (userRepository.findOneByUsername(user.getUsername()) != null) {
            Error error = new Error();
            error.setErrorMessage("User with name " + user.getUsername() + " already exist!");
//            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(500).body(error);

//            throw new AuthenticationException(error.getErrorMessage());
        }
        userRepository.save(user);
        String name = user.getUsername();
        List<User> all = userRepository.findAll();
        log.info("allUsers: " + all);
        log.info("username" + user.getUsername() + ", password: " + user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
