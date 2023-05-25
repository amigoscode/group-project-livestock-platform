
package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.entity.AccountEntity;
import com.amigoscode.livestockplatform.repository.AccountDao;
import com.amigoscode.livestockplatform.response.JwtResponse;
import com.amigoscode.livestockplatform.response.MessageResponse;
import com.amigoscode.livestockplatform.response.services.UserDetailsImpl;
import com.amigoscode.livestockplatform.security.jwt.JwtUtils;
import com.amigoscode.livestockplatform.service.UserService;
import com.amigoscodelivestock_platform.api.AuthApi;
import com.amigoscodelivestock_platform.model.Account;
import com.amigoscodelivestock_platform.model.Role;
import com.amigoscodelivestock_platform.model.User;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class AuthenticationController implements AuthApi {

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

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

        List<Role> strRoles = account.getRoles();
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
                    roles.add(Role.USER.getValue());

                    break;
                default:
                    throw new RuntimeException("User role " + role + " not valid");
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

    public ResponseEntity<com.amigoscodelivestock_platform.model.JwtResponse> authenticateUser(
            @Valid @RequestBody Account account) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }
}
