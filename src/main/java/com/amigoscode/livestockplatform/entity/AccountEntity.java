package com.amigoscode.livestockplatform.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "account", schema = "public", catalog = "postgres")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "role")
    private String role;
    @Basic
    @Column(name = "auth_method")
    private String authMethod;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && userId == that.userId && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(authMethod, that.authMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userId, role, authMethod);
    }
}
