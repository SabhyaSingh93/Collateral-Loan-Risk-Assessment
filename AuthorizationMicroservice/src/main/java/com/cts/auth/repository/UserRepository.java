package com.cts.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cts.auth.model.Admin;

@Repository
public interface UserRepository extends JpaRepository<Admin, String> {

}