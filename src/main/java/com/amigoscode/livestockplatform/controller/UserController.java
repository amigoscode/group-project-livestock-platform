package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.UserService;
import com.amigoscodelivestock_platform.api.UsersApi;
import com.amigoscodelivestock_platform.model.User;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class UserController implements UsersApi {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<List<User>> listUsers() {

        List<User> list = userService.listUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) {
        Optional<User> userOptional = userService.getUser(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + userId + " found.");
        }
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId, @Valid @RequestBody User user) {
        Optional<User> userOptional = userService.getUser(userId);
        if( userOptional.isEmpty()) {
            throw new IllegalArgumentException("No user with id " + userId + " found.");
        }
        User updatedUser = userService.updateUser(userId, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId){
        int status = getUser(userId).getStatusCode().value();
        if(HttpStatus.NOT_FOUND.value() == status ) {
            throw new IllegalArgumentException("The id user: " + userId + " does not exist.");
        }
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
