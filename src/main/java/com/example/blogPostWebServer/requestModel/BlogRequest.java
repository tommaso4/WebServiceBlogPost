package com.example.blogPostWebServer.requestModel;

import com.example.blogPostWebServer.enumPckg.Categoria;
import com.example.blogPostWebServer.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class BlogRequest {
    private Categoria category;
    private String title;
    private String  cover = "https://picsum.photos/200/300";
    private String content;
    private int minutes;
    private int idUser;
}
