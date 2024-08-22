package com.cybersoft.uniclub06.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // phương thức khơi tạo
public class SaveFileException extends RuntimeException {

    private String message;

}
