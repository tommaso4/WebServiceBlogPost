package com.example.blogPostWebServer.service;

import com.example.blogPostWebServer.model.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogSvc {
    List<Blog> blogList = new ArrayList<Blog>();

    public List<Blog> getAllBlogs() {
        return blogList;
    }

    public Blog getBlogById(int id) throws Exception {
        Optional<Blog> blog = blogList.stream().filter(blog1 -> blog1.getId() == id).findAny();
        if (blog.isPresent()) {
            return blog.get();
        } else {
            throw new Exception("Blog not found");
        }
    }

    public void saveBlog (Blog blog) {blogList.add(blog);}

    public Blog putChangedBlog(int id, Blog blog) throws Exception {
        Blog myBlog = getBlogById(id);
        myBlog.setTitle(blog.getTitle());
        myBlog.setCategory(blog.getCategory());
        myBlog.setContent(blog.getContent());
        myBlog.setMinutes(blog.getMinutes());
        return myBlog;
    }

    public void deleteBlog(int id) throws Exception {
        Blog myBlog = getBlogById(id);
        blogList.remove(myBlog);
    }

}
