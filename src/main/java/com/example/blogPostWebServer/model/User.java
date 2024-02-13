package com.example.blogPostWebServer.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
@Data
public class User {
    private final int id = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
    private String name;
    private String surname;
    private String email;
    private LocalDate dataNascita;
    private String avatar = createAvatar();

    public String createAvatar(){
        return "https://ui-avatars.com/api/?name="+this.name+"+"+this.surname;
    }

}
