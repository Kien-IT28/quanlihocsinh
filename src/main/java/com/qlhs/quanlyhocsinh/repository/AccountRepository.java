package com.qlhs.quanlyhocsinh.repository;

import com.qlhs.quanlyhocsinh.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
