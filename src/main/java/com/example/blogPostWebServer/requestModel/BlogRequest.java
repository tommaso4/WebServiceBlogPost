package com.example.blogPostWebServer.requestModel;

import com.example.blogPostWebServer.enumPckg.Categoria;
import com.example.blogPostWebServer.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogRequest {
    private Categoria category;

    @NotNull(message = "Title not specified")
    @NotEmpty(message = "Title is empty")
    private String title;
    private String  cover = "https://picsum.photos/200/300";
    @NotNull(message = "Content not specified")
    @NotEmpty(message = "Content is empty")
    private String content;
    @Max( message ="To much minutes",value = 35L)
    private int minutes;
    @NotNull(message = "Id not specified")
    private Integer idUser;
}
