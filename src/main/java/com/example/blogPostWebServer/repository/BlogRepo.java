package com.example.blogPostWebServer.repository;

import com.example.blogPostWebServer.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepo extends JpaRepository<Blog,Integer>, PagingAndSortingRepository<Blog,Integer> {
}
