package com.cybersoft.uniclub06.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenException extends RuntimeException{

    private String message;

}
