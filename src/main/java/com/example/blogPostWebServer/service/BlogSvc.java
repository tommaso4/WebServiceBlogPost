package com.example.blogPostWebServer.service;

import com.example.blogPostWebServer.exception.NotFoundException;
import com.example.blogPostWebServer.model.Blog;
import com.example.blogPostWebServer.model.User;
import com.example.blogPostWebServer.repository.BlogRepo;
import com.example.blogPostWebServer.requestModel.BlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogSvc {

    @Autowired
    private BlogRepo blogRepo;
    @Autowired
    private UserSvc userSvc;

    public Page<Blog> getAllBlogs(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    public Blog getBlogById (int id ) throws NotFoundException {
        return blogRepo.findById(id).orElseThrow(()-> new NotFoundException("No blog found for id: " + id));
    }

    public Blog saveBlog (BlogRequest blogRequest) throws NotFoundException {
        Blog blog = new Blog();
        User user = userSvc.getuserById(blogRequest.getIdUser());

        blog.setTitle(blogRequest.getTitle());
        blog.setCategory(blogRequest.getCategory());
        blog.setContent(blogRequest.getContent());
        blog.setMinutes(blogRequest.getMinutes());
        blog.setUser(user);
        return blogRepo.save(blog);
    }

    public Blog UpDateBlog (int id, BlogRequest blogRequest) throws NotFoundException {
        Blog blog = getBlogById(id);
        User user = userSvc.getuserById(blogRequest.getIdUser());

        blog.setTitle(blogRequest.getTitle());
        blog.setCategory(blogRequest.getCategory());
        blog.setContent(blogRequest.getContent());
        blog.setMinutes(blogRequest.getMinutes());
        blog.setUser(user);
        return blogRepo.save(blog);
    }

    public void deleteBlog (int id) throws NotFoundException {
        Blog blog = getBlogById(id);
        blogRepo.delete(blog);
    }
}
