package com.proxsoftware.webapp.controller;

import com.proxsoftware.webapp.Entity.User;
import com.proxsoftware.webapp.repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
public class UserService {
    private Logger log = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User user) throws AuthenticationException {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            throw new AuthenticationException("Username and password must be provided");
        }
        contactRepository.save(user);
        String name = user.getUsername();
        List<User> all = contactRepository.findAll();
        log.info("username" + user.getUsername() + ", password: " + user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
