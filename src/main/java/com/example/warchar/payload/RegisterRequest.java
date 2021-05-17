package com.example.warchar.payload;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max =30)
    private String password;
}
