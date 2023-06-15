package com.vn.assignmentjava5.service;


import com.vn.assignmentjava5.entities.Users;
import com.vn.assignmentjava5.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUser(){
        return usersRepository.findAll();
    }

    public void saveUser(Users users){
        usersRepository.save(users);
    }

    public void deleteUser(Integer id){
        usersRepository.deleteById(id);
    }

    public Optional<Users> findUserById(Integer id){
        return usersRepository.findById(id);
    }

    public Optional<Users> findUserByUsername(String username) {return usersRepository.findByUsername(username);}
}
