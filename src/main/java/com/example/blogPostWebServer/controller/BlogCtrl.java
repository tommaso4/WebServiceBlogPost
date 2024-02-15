package com.example.blogPostWebServer.controller;

import com.cloudinary.Cloudinary;
import com.example.blogPostWebServer.exception.NotFoundException;
import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.model.CustomResponse;
import com.example.blogPostWebServer.requestModel.BlogRequest;
import com.example.blogPostWebServer.service.BlogSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class BlogCtrl {

    @Autowired
    private BlogSvc blogSvc;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/blog")
    public ResponseEntity<CustomResponse> getBlogList(Pageable pageable) {
        Page<Blog> blogs = blogSvc.getAllBlogs(pageable);
        return CustomResponse.success(HttpStatus.OK.toString(), blogs, HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> getBlogById(@PathVariable int id) throws NotFoundException {
        Blog blog = blogSvc.getBlogById(id);
        return CustomResponse.success(HttpStatus.OK.toString(),blog,HttpStatus.OK);
    }

    @PostMapping("/blog")
    public ResponseEntity<CustomResponse> postBlog(@RequestBody @Validated BlogRequest blog, BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return CustomResponse.failure(bindingResult.getAllErrors().stream().map(obj->obj.getDefaultMessage()).toList().toString(),
                    HttpStatus.BAD_REQUEST);
        }
        Blog blog1 =blogSvc.saveBlog(blog);
        return CustomResponse.success(HttpStatus.OK.toString(),blog1,HttpStatus.OK);
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> putBlog(@PathVariable int id, @RequestBody @Validated BlogRequest blog,BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return CustomResponse.failure(bindingResult.getAllErrors().stream().map(obj->obj.getDefaultMessage()).toList().toString(),
                    HttpStatus.BAD_REQUEST);
        }
        Blog blog1 = blogSvc.UpDateBlog(id, blog);
        return CustomResponse.success(HttpStatus.OK.toString(),blog1, HttpStatus.OK);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable int id) throws NotFoundException {
        blogSvc.deleteBlog(id);
        return CustomResponse.emptyResponse("Blog id"+id + "deleted successfully", HttpStatus.OK);

    }

    @PatchMapping("/blog/{id}/upload")
    public ResponseEntity<CustomResponse> uploadBlog(@PathVariable int id, @RequestParam("upload")MultipartFile file) throws IOException, NotFoundException {
        String url = (String)cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
        Blog blog = blogSvc.upLoadCoverBlog(id, url);
        return CustomResponse.success(HttpStatus.OK.toString(),blog,HttpStatus.OK);
    }
}
