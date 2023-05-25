package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.AccountEntity;
import com.amigoscode.livestockplatform.entity.UsersEntity;
import com.amigoscode.livestockplatform.repository.AccountDao;
import com.amigoscode.livestockplatform.repository.UserDao;
import com.amigoscodelivestock_platform.model.Account;
import com.amigoscodelivestock_platform.model.Role;
import com.amigoscodelivestock_platform.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Transactional
    public Account getAccountByUsername(String username) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return buildAccountModel(accountEntity);

    }

    private Account buildAccountModel(AccountEntity accountEntity) {
        Account account = new Account();
        Gson gson = new Gson();

        String tmp = gson.toJson(accountEntity);
        account = gson.fromJson(tmp, Account.class);
        List<String> roles = Arrays.stream(accountEntity.getRole().split(",")).toList();

        account.setRoles(roles.stream().map(Role::fromValue).toList());
        return account;
    }

}
