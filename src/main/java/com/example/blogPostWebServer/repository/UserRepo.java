package com.example.blogPostWebServer.repository;

import com.example.blogPostWebServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends JpaRepository<User,Integer>, PagingAndSortingRepository<User,Integer> {
}
