
package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.entity.AccountEntity;
import com.amigoscode.livestockplatform.repository.AccountDao;
import com.amigoscode.livestockplatform.response.MessageResponse;
import com.amigoscode.livestockplatform.service.UserService;
import com.amigoscodelivestock_platform.api.AuthApi;
import com.amigoscodelivestock_platform.model.Account;
import com.amigoscodelivestock_platform.model.Role;
import com.amigoscodelivestock_platform.model.User;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class SignUpController implements AuthApi {

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserService userService;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public ResponseEntity<com.amigoscodelivestock_platform.model.MessageResponse> registerUser(
            @Valid @RequestBody Account account) {

        if (accountDao.existsByUsername(account.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (accountDao.existsByEmail(account.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        AccountEntity accountEntity = new AccountEntity();

        List<Role> strRoles = account.getRole();
        Set<String> roles = new HashSet<>();

        if (strRoles == null) {
            throw new RuntimeException("User role missing");
        } else {
            strRoles.forEach(role -> {
                switch (role.getValue()) {
                case "ROLE_ADMIN":
                    roles.add(Role.ADMIN.getValue());

                    break;
                case "ROLE_USER":
                    roles.add(Role.ADMIN.getValue());

                    break;
                default:
                    throw new RuntimeException("User role "+ role +" not valid");
                }
            });
        }

        accountEntity.setUsername(account.getUsername());
        accountEntity.setPassword(encoder.encode(account.getPassword()));
        accountEntity.setRole(StringUtils.join(roles, ','));
        User createdUser = userService.createUser(account.getUser());
        accountEntity.setUserId(createdUser.getId().intValue());
        accountDao.save(accountEntity);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
