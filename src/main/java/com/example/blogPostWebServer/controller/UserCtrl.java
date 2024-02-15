package com.example.blogPostWebServer.controller;

import com.example.blogPostWebServer.exception.NotFoundException;
import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.model.CustomResponse;
import com.example.blogPostWebServer.model.User;
import com.example.blogPostWebServer.requestModel.BlogRequest;
import com.example.blogPostWebServer.requestModel.UserRequest;
import com.example.blogPostWebServer.service.BlogSvc;
import com.example.blogPostWebServer.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCtrl {

    @Autowired
    private UserSvc userSvc;

    @GetMapping("/user")
    public ResponseEntity<CustomResponse> getAllUsers(Pageable pageable) {
        Page<User> users = userSvc.getAllUsers(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable int id) throws NotFoundException {
            User user = userSvc.getuserById(id);
            return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<CustomResponse> postUser(@RequestBody @Validated UserRequest user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomResponse.failure(bindingResult.getAllErrors().stream().map(obj-> obj.getDefaultMessage()).toList().toString(),
                    HttpStatus.BAD_REQUEST);
        }
            User user1 =userSvc.saveUser(user);
            return CustomResponse.success(HttpStatus.OK.toString(),user1,HttpStatus.OK);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<CustomResponse> putUser(@PathVariable int id, @RequestBody @Validated UserRequest user, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()){
            return CustomResponse.failure(bindingResult.getAllErrors().stream().map(obj-> obj.getDefaultMessage()).toList().toString(),
                    HttpStatus.BAD_REQUEST);
        }
            User user1 = userSvc.updateUser(id, user);
            return CustomResponse.success(HttpStatus.OK.toString(),user1, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable int id) throws NotFoundException {

        userSvc.deleteUser(id);
        return CustomResponse.emptyResponse("User id"+id + "deleted successfully", HttpStatus.OK);

    }
}
