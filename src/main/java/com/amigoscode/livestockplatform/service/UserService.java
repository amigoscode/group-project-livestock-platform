package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.UsersEntity;
import com.amigoscode.livestockplatform.repository.UserDao;
import com.amigoscodelivestock_platform.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listUsers() {
        List<UsersEntity> usersEntities = userDao.findAll();
        return usersEntities.stream().map(this::buildUserModel).toList();
    }

    public Optional<User> getUser(int id) {
        Optional<User> user = this.listUsers().stream().filter(u -> id == u.getId().intValue()).findFirst();
        return user;
    }

    @Transactional
    public User createUser(User user) {
        Gson gson = new Gson();
        String tmp = gson.toJson(user);
        UsersEntity usersEntity = userDao.save(gson.fromJson(tmp, UsersEntity.class));
        return buildUserModel(usersEntity);
    }

    @Transactional
    public User updateUser(Integer id, User user) {
        user.setId(BigDecimal.valueOf(id));
        Gson gson = new Gson();
        String tmp = gson.toJson(user);

        UsersEntity usersEntity = userDao.save(gson.fromJson(tmp, UsersEntity.class));

        return buildUserModel(usersEntity);
    }

    public void deleteUser(Integer id) {
        userDao.deleteById(id);
    }

    private User buildUserModel(UsersEntity usersEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(usersEntity);
        return gson.fromJson(tmp, User.class);
    }

}
