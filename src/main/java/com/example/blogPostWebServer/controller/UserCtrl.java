package com.example.blogPostWebServer.controller;

import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.model.User;
import com.example.blogPostWebServer.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCtrl {
    @Autowired
    private UserSvc userSvc;
    @GetMapping("/user")
    public List<User> getUserList() {return userSvc.getAllUsers();}
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) throws Exception {return userSvc.getUserById(id);}
    @PostMapping("/user")
    public void postUser(@RequestBody User blog){userSvc.saveUser(blog);}
    @PutMapping("/user/{id}")
    public User putUser(@PathVariable int id, @RequestBody User blog) throws Exception {
        return userSvc.putChangedUser(id,blog);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser (@PathVariable int id) throws Exception {
        userSvc.deleteUser(id);
    }

}
