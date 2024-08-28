package com.cybersoft.uniclub06.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileNotFoundException extends RuntimeException{
    private String message = "Lỗi không tìm thấy file";
}
