package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.UsersApi;
import com.amigoscodelivestock_platform.api.UsersApiDelegate;
import com.amigoscodelivestock_platform.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-09T17:10:08.534310300+02:00[Europe/Paris]")
@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class UsersApiController implements UsersApi {


    public ResponseEntity<List<User>> listUsers() {
        User user = new User();
        List list = new ArrayList();
        list.add(user);
        ResponseEntity responseEntity = new ResponseEntity(list, HttpStatus.OK);
        return responseEntity;
    }
}
