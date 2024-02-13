package com.example.blogPostWebServer.controller;

import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.service.BlogSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogCtrl {

    @Autowired
    private BlogSvc blogSvc;

    @GetMapping("/blog")
    public List<Blog> getBlogList() {return blogSvc.getAllBlogs();}
    @GetMapping("/blog/{id}")
    public Blog getBlogById(@PathVariable int id) throws Exception {return blogSvc.getBlogById(id);}
    @PostMapping("/blog")
    public void postBlog(@RequestBody Blog blog){blogSvc.saveBlog(blog);}
    @PutMapping("/blog/{id}")
    public Blog putBlog(@PathVariable int id, @RequestBody Blog blog) throws Exception {
        return blogSvc.putChangedBlog(id,blog);
    }
    @DeleteMapping("/blog/{id}")
    public void deleteBlog (@PathVariable int id) throws Exception {
        blogSvc.deleteBlog(id);
    }
}
