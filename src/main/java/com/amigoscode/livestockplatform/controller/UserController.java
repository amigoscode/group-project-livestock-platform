package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.UsersApi;
import com.amigoscodelivestock_platform.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class UserController implements UsersApi {

    public ResponseEntity<List<User>> listUsers() {
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
