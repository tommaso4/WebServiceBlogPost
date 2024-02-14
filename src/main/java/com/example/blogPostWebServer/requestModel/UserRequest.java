package com.example.blogPostWebServer.requestModel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private LocalDate dataNascita;
    private String avatar;
    public UserRequest(){
        this.avatar = createAvatar();
    }
    public String createAvatar(){
        return "https://ui-avatars.com/api/?name="+name+"+"+surname;
    }


}
