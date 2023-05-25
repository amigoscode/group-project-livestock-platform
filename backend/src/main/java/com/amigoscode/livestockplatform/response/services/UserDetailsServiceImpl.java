package com.amigoscode.livestockplatform.response.services;

import com.amigoscode.livestockplatform.entity.AccountEntity;
import com.amigoscode.livestockplatform.repository.AccountDao;
import com.amigoscode.livestockplatform.service.AccountService;
import com.amigoscodelivestock_platform.model.Account;
import com.amigoscodelivestock_platform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccountByUsername(username);

        return UserDetailsImpl.build(account);
    }

}