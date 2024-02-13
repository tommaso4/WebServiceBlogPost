package com.example.blogPostWebServer.service;

import com.example.blogPostWebServer.model.User;
import com.example.blogPostWebServer.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSvc {
    List<User> userList = new ArrayList<User>();

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(int id) throws Exception {
        Optional<User> user = userList.stream().filter(user1 -> user1.getId() == id).findAny();
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public void saveUser (User user) {userList.add(user);}

    public User putChangedUser(int id, User user) throws Exception {
        User myUser = getUserById(id);
        myUser.setName(user.getName());
        myUser.setSurname(user.getSurname());
        myUser.setEmail(user.getEmail());
        myUser.setDataNascita(user.getDataNascita());
        return myUser;
    }

    public void deleteUser(int id) throws Exception {
        User myUser = getUserById(id);
        userList.remove(myUser);
    }

}
