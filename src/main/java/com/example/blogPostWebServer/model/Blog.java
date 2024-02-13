package com.example.blogPostWebServer.model;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class Blog {
    private final int id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    private Categoria category;
    private String title;
    private String  cover = "https://picsum.photos/200/300";
    private String content;
    private int minutes;
}
