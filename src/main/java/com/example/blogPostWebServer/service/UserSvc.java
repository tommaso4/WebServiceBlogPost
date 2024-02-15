package com.example.blogPostWebServer.service;

import com.example.blogPostWebServer.exception.NotFoundException;
import com.example.blogPostWebServer.model.User;
import com.example.blogPostWebServer.repository.UserRepo;
import com.example.blogPostWebServer.requestModel.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class UserSvc {

    @Autowired
    private UserRepo userRepo;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public User getuserById (int id) throws NotFoundException {
        return userRepo.findById(id).orElseThrow(()-> new NotFoundException("User with id: " + id + " not found"));
    }

    public User saveUser (UserRequest userRequest) {
        User user = new User();

        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setDataNascita(userRequest.getDataNascita());
        user.setAvatar(user.createAvatar(userRequest.getName(),userRequest.getSurname()));
        return userRepo.save(user);
    }

    public User updateUser(int id,UserRequest userRequest) throws NotFoundException {
        User user = getuserById(id);

        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setDataNascita(userRequest.getDataNascita());
        user.setAvatar(userRequest.getAvatar());

        return userRepo.save(user);
    }

    public void deleteUser (int id) throws NotFoundException {
        User user = getuserById(id);
        userRepo.delete(user);
    }

}
