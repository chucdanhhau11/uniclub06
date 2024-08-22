package com.cybersoft.uniclub06.exception;

import com.cybersoft.uniclub06.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// tất cả exception ném ra sẽ chạy trong này nếu có @RestControllerAdvice
@RestControllerAdvice
public class CentralException {

    // tất cả các lỗi ném ra dạng RuntimeException thì sẽ chạy hàm này
    @ExceptionHandler({SaveFileException.class})
    public ResponseEntity<?> handleException(Exception e) {
        BaseResponse response = new BaseResponse();

        response.setStatusCode(500);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({AuthenException.class})
    public ResponseEntity<?> handleException1(Exception e) {
        BaseResponse response = new BaseResponse();

        response.setStatusCode(200);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
