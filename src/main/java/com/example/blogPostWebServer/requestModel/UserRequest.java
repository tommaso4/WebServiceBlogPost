package com.example.blogPostWebServer.requestModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Normalized;

import java.time.LocalDate;

@Data
public class UserRequest {
    @NotNull(message = "Name not specified")
    @NotEmpty(message = "Name is empty")
    private String name;
    @NotNull(message = "Surname not specified")
    @NotEmpty(message = "Surname is empty")
    private String surname;
    @Email
    @NotNull(message = "Email not specified")
    @NotEmpty(message = "Email is empty")
    private String email;
    @NotNull(message = "DataNascita not specified")
    private LocalDate dataNascita;

    private String avatar;


}
