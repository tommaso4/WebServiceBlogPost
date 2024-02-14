package com.example.blogPostWebServer.controller;

import com.example.blogPostWebServer.exception.NotFoundException;
import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.model.CustomResponse;
import com.example.blogPostWebServer.requestModel.BlogRequest;
import com.example.blogPostWebServer.service.BlogSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogCtrl {

    @Autowired
    private BlogSvc blogSvc;

    @GetMapping("/blog")
    public ResponseEntity<CustomResponse> getBlogList(Pageable pageable) {
        try {
            Page<Blog> blogs = blogSvc.getAllBlogs(pageable);
            return CustomResponse.success(HttpStatus.OK.toString(), blogs, HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> getBlogById(@PathVariable int id){
        try {
            Blog blog = blogSvc.getBlogById(id);
            return CustomResponse.success(HttpStatus.OK.toString(),blog,HttpStatus.OK);
        }catch (NotFoundException e) {
            return CustomResponse.failure(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/blog")
    public ResponseEntity<CustomResponse> postBlog(@RequestBody BlogRequest blog) {
        try {
            Blog blog1 =blogSvc.saveBlog(blog);
            return CustomResponse.success(HttpStatus.OK.toString(),blog,HttpStatus.OK);
        } catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> putBlog(@PathVariable int id, @RequestBody BlogRequest blog)  {
        try {
            Blog blog1 = blogSvc.UpDateBlog(id, blog);
            return CustomResponse.success(HttpStatus.OK.toString(),blog1, HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.failure(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable int id){
        try {
            blogSvc.deleteBlog(id);
            return CustomResponse.emptyResponse("Blog id"+id + "deleted successfully", HttpStatus.OK);
        }catch (NotFoundException e){
            return CustomResponse.failure(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CustomResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
