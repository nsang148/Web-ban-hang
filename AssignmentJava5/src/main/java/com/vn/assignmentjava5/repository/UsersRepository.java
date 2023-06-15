package com.vn.assignmentjava5.repository;

import com.vn.assignmentjava5.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
    Users findByUsernameAndPassword(String userName, String passWord);

     Optional<Users> findByUsername(String username);
}
