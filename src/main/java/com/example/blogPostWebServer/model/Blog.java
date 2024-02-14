package com.example.blogPostWebServer.model;

import com.example.blogPostWebServer.enumPckg.Categoria;
import jakarta.persistence.*;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private Categoria category;
    private String title;
    private String  cover = "https://picsum.photos/200/300";
    private String content;
    private int minutes;
    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;
}
