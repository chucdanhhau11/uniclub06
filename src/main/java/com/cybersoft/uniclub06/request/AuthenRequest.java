package com.cybersoft.uniclub06.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenRequest(

        @NotNull(message = "Email không được null")
        @NotEmpty(message = "Email không được phép rỗng")
        @Email
        String email,

        @NotNull(message = "Pass không được phép null")
        @NotEmpty(message = "Pass không được phép rỗng")
        String password) {



}
