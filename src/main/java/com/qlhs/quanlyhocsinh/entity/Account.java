package com.qlhs.quanlyhocsinh.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "Account")
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int mhs;

    String password;

    @ManyToMany
    Set<Role> roles;

    // Constructor không tham số
    public Account() {}

    // Constructor với tham số
    public Account(int mhs, String password, Set<Role> roles) {
        this.mhs = mhs;
        this.password = password;
        this.roles = roles;
    }
}
