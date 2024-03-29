package com.example.blogPostWebServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dataNascita;
    private String avatar;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Blog> blogList;


    public String createAvatar(String name, String surname){

        return "https://ui-avatars.com/api/?name="+name+"+"+surname;
    }

}
