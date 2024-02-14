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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCtrl {

    @Autowired
    private UserSvc userSvc;

    @GetMapping("/user")
    public ResponseEntity<CustomResponse> getAllUsers(Pageable pageable) {
        try {
            Page<User> users = userSvc.getAllUsers(pageable);
            return CustomResponse.success(HttpStatus.OK.toString(), users, HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable int id){
        try {
            User user = userSvc.getuserById(id);
            return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
        }catch (NotFoundException e) {
            return CustomResponse.failure(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<CustomResponse> postUser(@RequestBody UserRequest user) {
        try {
            User user1 =userSvc.saveUser(user);
            return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<CustomResponse> putUser(@PathVariable int id, @RequestBody UserRequest user)  {
        try {
            User user1 = userSvc.updateUser(id, user);
            return CustomResponse.success(HttpStatus.OK.toString(),user1, HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.failure(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable int id){
        try {
            userSvc.deleteUser(id);
            return CustomResponse.emptyResponse("User id"+id + "deleted successfully", HttpStatus.OK);
        }catch (NotFoundException e){
            return CustomResponse.failure(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
